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
import router from '../router';

export default defineComponent ({
  name: 'Torneo',
  components: {
    TorneoCarta
  },
  setup() {
    const tournamentData = ref<Tournament[]>([]);
    const searchQuery = ref('');

   (async () => {
      try {
        const params = new URLSearchParams(window.location.search);
        searchQuery.value = params.get('q') || '';

        if (searchQuery.value !== '') {
          // Si hay una consulta de búsqueda, filtrar los torneos por nombre
          const tournaments = await TournamentService.getPublicContent();
          const filteredTournaments = tournaments.filter((tournament: { name: string; }) =>
            tournament.name.toLowerCase().includes(searchQuery.value.toLowerCase())
          );
          tournamentData.value = filteredTournaments;
        } else {
          // Si no hay consulta de búsqueda, cargar todos los torneos
          tournamentData.value = (await TournamentService.getPublicContent()) as Tournament[];
        }
       
      } catch (error) {
        console.error(error);
      }
    }
    // Falta automatizar el reload!!!!!!!!!!!!
  )();

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