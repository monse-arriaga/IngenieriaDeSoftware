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

