<template>
  <div class="tournament-grid">
    <TorneoCarta v-for="tournament in tournamentData" :key="tournament.name" :tournament="tournament" />
  </div>
</template>

<script lang="ts">
import TorneoCarta from './TorneoCarta.vue';
import Tournament from '../types/Tournament';
import { defineComponent, ref } from 'vue';
import TournamentService from '../services/TournamentService';

export default defineComponent ({
  name: 'Torneo',
  components: {
    TorneoCarta
  },
  setup() {
    const tournamentData = ref<Tournament[]>([]);
    (async () => {
      try {
        //console.log(TournamentService.getPublicContent())
        tournamentData.value = (await TournamentService.getPublicContent()) as Tournament[];
        //console.log(TournamentService.getTournamentByName("conejos"))
      } catch (error) {
        console.error(error);
      }
    })();

    return {
      tournamentData
    };
  }
});
</script>

<style scoped>
.tournament-grid {
  display: grid;
  grid-template-columns: repeat(3, 2000fr); /* Dos columnas */
  grid-auto-rows: minmax(100px, auto);
  grid-gap: 20px;
  justify-items: center;
  align-items: center;
}
</style>