<template>
    <div>
      <div style="margin-top: 40px;"></div>
      <h1>Ganador</h1><div style="margin-top: 40px;"></div>
      <div class="input-container">
      <q-checkbox dark v-model="isPlayer1"  :label="player1name" />

      <q-checkbox dark v-model="isPlayer2" :label="player2name" />

      <q-btn color="primary" @click="submit" label="Decidir" />
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { Match, Result } from 'brackets-model';
  import { defineComponent } from 'vue';
  import MatchService from '../services/MatchService';
  import ParticipantService from '../services/ParticipantService';
  import Participant from '../types/Participant';
  import tournamentStorage from '../store/tournament';
  import { BracketsManager } from 'brackets-manager';
  export default defineComponent({
  name: 'DecidirGanador',
  data() {
      return {
        isPlayer1: false,
        isPlayer2: false,
        bracket: {
          skipFirstRound: false,
        },
        player1name: '',
        player2name: '',
      };
    },
    async mounted() {
      const names = await this.getMatchParticipants();
      this.player1name = names[0];
      this.player2name = names[1];
    },
    methods: {
      async getMatchParticipants(): Promise<string[]> {
        const match = await MatchService.select(parseInt(this.$route.params.match as string));
        const matchT: Match[] = match ? (Array.isArray(match) ? match : [match]) : [];
        const participant1 = await ParticipantService.select(matchT[0].opponent1?.id as number) as any as Participant[];
        const participant2 = await ParticipantService.select(matchT[0].opponent2?.id as number) as any as Participant[];
        return [participant1[0].name, participant2[0].name];
      },
      async submit() {
        const storage = new tournamentStorage();
        const manager = new BracketsManager(storage);
        const op1 = {
          score: this.isPlayer1 && this.isPlayer2 ? 1 : (this.isPlayer1 ? 2 : 0),
          result: this.isPlayer1 && this.isPlayer2 ? 'draw' : (this.isPlayer1 ? 'win' : 'loss')
        }
        const op2 = {
          score: this.isPlayer1 && this.isPlayer2 ? 1 : (this.isPlayer1 ? 0 : 2),
          result: this.isPlayer1 && this.isPlayer2 ? 'draw' : (this.isPlayer1 ? 'loss' : 'win')
        }
        await manager.update.match({
        id: parseInt(this.$route.params.match as string), 
        opponent1: { score: op2.score, result: op1.result as Result},
        opponent2: { score: op1.score, result: op2.result as Result},
       });
      },
    },
  });
  </script>

  <style scoped>
  .input-container {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }
  </style>