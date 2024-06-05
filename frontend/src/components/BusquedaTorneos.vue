<template>
  <div class="q-pa-md">
    <div class="q-gutter-md row items-start">
      <div style="min-width: 250px; max-width: 300px; margin-top: 45px;">
        <q-select dark filled v-model="juego" multiple :options="optionsJuego" label="Juego" use-chips stack-label />
      </div>

      <div style="min-width: 250px; max-width: 300px; margin-top: 45px;">
        <q-select dark filled label="Modalidad" v-model="modalidad" multiple :options="optionsModalidad" use-chips stack-label />
      </div>

      <div style="min-width: 250px; max-width: 300px; margin-top: 45px;">
        <q-select dark filled v-model="estado" :options="optionsEstado" label="Estado" use-chips stack-label />
      </div>
    </div>
  </div>
  <div class="tournament-grid">
    <TorneoCarta v-for="tournament in paginatedTournaments" :key="tournament.name" :tournament="tournament" />
  </div>
  <div class="q-pa-lg flex flex-center">
    <q-pagination
      dark
      v-model="currentPage"
      :max="totalPages"
      input
    />
  </div>
</template>


<script lang="ts">
import TorneoCarta from './TorneoCarta.vue';
import Tournament from '../types/Tournament';
import { defineComponent, ref, watch, computed } from 'vue';
import TournamentService from '../services/TournamentService';
import { useRoute } from 'vue-router';

export default defineComponent({
  name: 'Torneo',
  components: {
    TorneoCarta
  },
  setup() {
    const tournamentData = ref<Tournament[]>([]);
    const searchQuery = ref('');
    const juego = ref<string[]>([]);
    const modalidad = ref<string[]>([]);
    const estado = ref<string[]>(['abierto']);
    const route = useRoute();

    const currentPage = ref(1);
    const itemsPerPage = 12;

    const loadTournaments = async () => {
      try {
        const params = new URLSearchParams(window.location.search);
        searchQuery.value = params.get('q') || '';

        const tournaments = await TournamentService.getPublicContent();

        let filteredTournaments = tournaments;

        // Filtrar por nombre
        if (searchQuery.value !== '') {
          filteredTournaments = filteredTournaments.filter((tournament: { name: string; }) =>
            tournament.name.toLowerCase().includes(searchQuery.value.toLowerCase())
          );
        }

        // Filtrar por juego
        if (juego.value.length > 0) {
          filteredTournaments = filteredTournaments.filter((tournament: { tournamentGame: string; }) =>
            juego.value.includes(tournament.tournamentGame)
          );
        }

        // Filtrar por modalidad
        if (modalidad.value.length > 0) {
          filteredTournaments = filteredTournaments.filter((tournament: { tournamentType: string; }) =>
            modalidad.value.includes(tournament.tournamentType)
          );
        }

        // Filtrar por estado
        if (estado.value.length > 0) {
          filteredTournaments = filteredTournaments.filter((tournament: { state: string; }) =>
            estado.value.includes(tournament.state)
          );
        }

        tournamentData.value = filteredTournaments;
      } catch (error) {
        console.error(error);
      }
    };

    // Computed property to calculate paginated tournaments
    const paginatedTournaments = computed(() => {
      const start = (currentPage.value - 1) * itemsPerPage;
      const end = start + itemsPerPage;
      return tournamentData.value.slice(start, end);
    });

    // Computed property to calculate total pages
    const totalPages = computed(() => {
      return Math.ceil(tournamentData.value.length / itemsPerPage);
    });

    // Observar los cambios en los filtros
    watch(
      () => [route.query.q, juego.value, modalidad.value, estado.value],
      () => {
        searchQuery.value = route.query.q as string || '';
        loadTournaments();
        currentPage.value = 1; // Reset to the first page when filters change
      },
      { immediate: true }
    );

    return {
      tournamentData,
      searchQuery,
      juego,
      modalidad,
      estado,
      currentPage,
      paginatedTournaments,
      totalPages,
      optionsModalidad: ['Eliminación Doble', 'Eliminación Directa', 'Liga'],
      optionsJuego: ['Minecraft', 'Fall Guys', 'Fortnite'],
      optionsEstado: ['abierto', 'cerrado']
    };
  }
});
</script>


<style scoped>
.tournament-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* Tres columnas */
  grid-auto-rows: minmax(100px, auto);
  grid-gap: 20px;
  justify-items: center;
  align-items: center;
}
</style>
