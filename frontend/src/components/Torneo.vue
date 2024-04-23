<template>
  <div>
    <TorneoCarta :tournament="tournamentData" />
  </div>
</template>

<script lang="ts">
import TorneoCarta from './TorneoCarta.vue';
import Tournament from '../types/Tournament';
import { defineComponent, ref } from 'vue';

export default defineComponent ({
  name: 'Torneo',
  components: {
    TorneoCarta
  },
  setup() {
    const tournamentData = ref<Tournament>({
      name: 'VALORANT UNDERGROUND LAN #2 QUALY #1',
      description: 'Lorem ipsum dolor sit amet',
      tournamentType: 'eliminacion directa',
      players: 10,
      state: "live",
      playersBT: 0,
      inPlayers: 0,
      time: "10:00 AM",
      date: "1-1-2000",
      prize: 0
    });

    return {
      tournamentData
    };
  }
});
</script>

