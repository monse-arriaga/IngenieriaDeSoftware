<template>
  <div class="q-pa-md">
    <div class="q-gutter-md row items-start">
      <div style="min-width: 250px; max-width: 300px; margin-top: 45px;">

        <q-select dark
          filled
          v-model="juego"
          multiple
          :options="optionsJuego"
          label="Juego"
          use-chips
          stack-label
        />
      </div>

      <div style="min-width: 250px; max-width: 300px; margin-top: 45px;">

        <q-select dark
          filled
          label="Modalidad"
          v-model="modalidad"
          multiple
          :options="optionsModalidad"
          use-chips
          stack-label
        />
      </div>

      <div style="min-width: 250px; max-width: 300px; margin-top: 45px;">

        <q-select dark
          filled
          v-model="estado"
          :options="optionsEstado"
          label="Estado"
          use-chips
          stack-label
        />
      </div>
    </div>
  </div>
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
      tournamentData,
      estado: ref('abierto'),
      single: ref('Selecciona una'),
      juego: ref(['Minecraft']),
      modalidad: ref(['Torneo de Liga']),
      optionsModalidad: [
        'Torneo de Liga', 'Eliminación Directa', 'Liga y Eliminatoria'
      ],
      optionsJuego: [
        'Minecraft', 'Valorant', 'Fortnite'
      ],
      optionsEstado: [
        'abierto', 'cerrado'
      ]
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