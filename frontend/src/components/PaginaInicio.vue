<template>
  <div class="carousel-container">
  <Carousel :slides-per-page="10">
  <Slide v-for="tournament in tournamentData" :key="tournament.name">
        <div class="carousel__item">
          <TorneoCarta :tournament="tournament" />
        </div>
    </Slide>
    <template #addons>
      <Navigation />
    </template>
    <Navigation />
  </Carousel>
</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import TorneoCarta from './torneoCarrusel.vue';
import Tournament from '../types/Tournament';
import { Carousel, Navigation, Slide } from 'vue3-carousel';
import TournamentService from '../services/TournamentService';

import 'vue3-carousel/dist/carousel.css';

const tournamentData = ref<Tournament[]>([]);

onMounted(async () => {
  try {
    tournamentData.value = await TournamentService.getPublicContent() as Tournament[];
  } catch (error) {
    console.error(error);
  }
});
</script>

<style>
.carousel-container {
  margin-top: 50px; /* Ajusta este valor según sea necesario */
  width: 100%;
}
.carousel__item {
  min-height: 0px;
  height: 8%;
  width: 8%;
  background-color: #646cffaa;
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 10px;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 1px solid rgba(253, 252, 255, 0);
  color: white; /* Cambia el color de las flechas a blanco */
  background: none; /* Asegúrate de que el fondo sea transparente */
}

.carousel__prev {
  left: 60px; /* Ajusta la posición horizontal */
}

.carousel__next {
  right:60px; /* Ajusta la posición horizontal */
}

.carousel__prev, .carousel__next {
  top: 50%; /* Centra verticalmente las flechas */
  transform: translateY(-50%);
}

.carousel__prev:hover, .carousel__next:hover {
  background-color: rgba(255, 255, 255, 0.801); /* Agrega un efecto hover si lo deseas */
}
</style>
