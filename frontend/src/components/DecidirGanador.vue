<template>
  <div>
    <div style="margin-top: 80px;"></div>
    <h2 class="text-cyan">¿Quién fue el ganador en esta contienda?</h2>
    <div style="margin-top: 40px;"></div>
    <div class="input-container">
      <div class="player-container">
        <img :src="player1image" class="player-image" alt="Player 1" />
        <q-checkbox dark class="text-cyan" v-model="isPlayer1" :label="player1name" />
      </div>
      <div class="player-container">
        <img :src="player2image" class="player-image" alt="Player 2" />
        <q-checkbox dark class="text-cyan" v-model="isPlayer2" :label="player2name" />
      </div>
    </div>
    <div class="button-container">
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
      player1image: '',
      player2image: '',
      playerImages1: [
        'https://img.freepik.com/fotos-premium/retrato-perros-malos-chaqueta-sobre-fondo-rojo-creada-ia-generativa_90099-2248.jpg',
        'https://cdn.meadd.com/photos/full/65716036.jpg',
        'https://holatelcel.com/wp-content/uploads/2020/09/among-us-rojo.jpg',
        // Añade más URLs de imágenes aquí
      ],
      playerImages2: [
        'https://pbs.twimg.com/media/EZLXRyMXQAABOJ4?format=jpg&name=large',
        'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/9eb09607-618f-4964-a3e1-0b1a70f48436/d9y2cse-f268c448-f001-47d2-b03d-68bc220fdc7a.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzllYjA5NjA3LTYxOGYtNDk2NC1hM2UxLTBiMWE3MGY0ODQzNlwvZDl5MmNzZS1mMjY4YzQ0OC1mMDAxLTQ3ZDItYjAzZC02OGJjMjIwZmRjN2EucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.Z6vr6-f3USPi97V9nEv3Rs0BQzIdvAEJJ1s87BXFA-0',
        'https://www.elplural.com/uploads/s1/79/53/89/file-20181211-76962-7m31me.jpeg',
        // Añade más URLs de imágenes aquí
      ],
    };
  },
  async mounted() {
    const names = await this.getMatchParticipants();
    this.player1name = names[0];
    this.player2name = names[1];
    this.player1image = this.getRandomImage1();
    this.player2image = this.getRandomImage2();
  },
  methods: {
    getRandomImage1(): string {
      const randomIndex = Math.floor(Math.random() * this.playerImages1.length);
      return this.playerImages1[randomIndex];
    },
    getRandomImage2(): string {
      const randomIndex = Math.floor(Math.random() * this.playerImages2.length);
      return this.playerImages2[randomIndex];
    },
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
        opponent1: { score: op2.score, result: op1.result as Result },
        opponent2: { score: op1.score, result: op2.result as Result },
      });
    },
  },
});
</script>

<style scoped>
.text-cyan {
  color: cyan;
}

.input-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 20px 0;
}

.player-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.player-image {
  width: 450px; /* Aumenta el tamaño de la imagen */
  height: 450px; /* Aumenta el tamaño de la imagen */
  border-radius: 50%;
  margin-bottom: 10px;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
