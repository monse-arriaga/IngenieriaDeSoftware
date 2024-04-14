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
      about: 'Lorem ipsum dolor sit amet',
      tournamentType: 'eliminacion directa',
      noPlayers: 10,
      status: "live",
      playerType: "single",
      inPlayers: 0,
      date: new Date(),
      prize: 0
    });

    return {
      tournamentData
    };
  }
});
</script>

