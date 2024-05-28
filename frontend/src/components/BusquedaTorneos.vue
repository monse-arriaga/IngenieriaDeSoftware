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
    <TorneoCarta v-for="tournament in tournamentData" :key="tournament.name" :tournament="tournament" />
  </div>
</template>

<script lang="ts">
import TorneoCarta from './TorneoCarta.vue';
import Tournament from '../types/Tournament';
import { defineComponent, ref, watch } from 'vue';
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
    const route = useRoute();

    const loadTournaments = async () => {
      try {
        const params = new URLSearchParams(window.location.search);
        searchQuery.value = params.get('q') || '';

        const tournaments = await TournamentService.getPublicContent();
        if (searchQuery.value !== '') {
          const filteredTournaments = tournaments.filter(tournament =>
            tournament.name.toLowerCase().includes(searchQuery.value.toLowerCase())
          );
          tournamentData.value = filteredTournaments;
        } else {
          tournamentData.value = tournaments;
        }
      } catch (error) {
        console.error(error);
      }
    };

    // Observar los cambios en searchQuery
    watch(() => route.query.q, () => {
      searchQuery.value = route.query.q as string || '';
      loadTournaments();
    }, { immediate: true });

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
