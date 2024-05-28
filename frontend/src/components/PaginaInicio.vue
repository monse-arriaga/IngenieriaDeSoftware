<template>
  
  <div class="carousel-container">
    <Carousel :slides-per-page="5"> <!-- Ajusta el número de slides por página según sea necesario -->
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
  <h2 class="header">Administra tus propios torneos</h2>
  <section class="dark:bg-gray-100 dark:text-gray-800">
    <div class="container flex flex-col-reverse mx-auto lg:flex-row">
      <div class="info-container flex flex-col px-6 py-8 space-y-6 rounded-sm sm:p-8 lg:p-12 lg:w-1/2 xl:w-2/5 dark:text-gray-50 dark:bg-blue-600">
        <div class="info-item flex space-x-2 sm:space-x-4">
          <div class="space-y-2">
            <q-icon name="diversity_3" size="24px"/>
            <span class="info-text">  Listo para jugar en equipos</span>
            <p class="leading-snug">¿Quieres jugar con tus amigos? No te preocupes, únete a su grupo</p>
          </div>
          
        </div>

        <div class="info-item flex space-x-2 sm:space-x-4">
          <div class="space-y-2">
            <q-icon name="recommend" size="24px"/>
            <span class="info-text">  Fácil e intuitivo</span>
            <p class="leading-snug">Si quieres crear un torneo solo presiona el símbolo de crear y podrás personalizar tu propio torneo</p>
          </div>
          
        </div>

        <div class="info-item flex space-x-2 sm:space-x-4">
          <div class="space-y-2">
            <q-icon name="public" size="24px"/>
            <span class="info-text">  Un mundo lleno de aventuras </span>
            <p class="leading-snug">¿Quieres pasar un buen rato? Únete a uno de los torneos ya creados</p>
          </div> 
        </div>

      </div>
      <div class="lg:w-1/2 xl:w-3/5 dark:bg-gray-100">
        <div class="flex items-center justify-center p-4 md:p-8 lg:p-12">
          <img src="https://www.universidadviu.com/sites/universidadviu.com/files/images/qu%C3%A9%20es%20un%20gamer.jpg" alt="" class="rounded-lg shadow-lg dark:bg-gray-500 aspect-video sm:min-h-96">
        </div>
      </div>
    </div>
  </section>
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
.header {
  position: absolute;
  top: 622px; /* Ajusta la posición vertical */
  left: 190px; /* Ajusta la posición horizontal */
  color: white;
  font-size: 45px; /* Ajusta el tamaño de la fuente según sea necesario */
  margin: 0;
}

.carousel-container {
  margin-top: 80px; /* Ajusta según sea necesario */
  width: 100%;
  height: 600px; /* Ajusta la altura según sea necesario */
  position: relative;
}

.carousel__item {
  height: 100%;
  width: 100%;
  background-color: #646cffaa;
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 1px;
  height: 100%;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 1px solid rgba(253, 252, 255, 0);
  color: white;
  background: none;
}

.info-text {
  font-size: 1.2rem; /* Tamaño de fuente más grande */
}

.carousel__prev {
  left: 60px;
}

.carousel__next {
  right: 60px;
}

.carousel__prev, .carousel__next {
  top: 50%;
  transform: translateY(-50%);
}

.carousel__prev:hover, .carousel__next:hover {
  background-color: rgba(255, 255, 255, 0.801);
}

.torneo-carta img {
  width: 100%;
  height: auto;
  border-radius: 8px;
  object-fit: cover;
}

/* Nuevos estilos */
.info-container {
  background-color: #646cffaa; /* Fondo verde */
  color: white; /* Texto blanco */
}

.info-item p {
  font-size: 1.2rem; /* Ajusta el tamaño de la fuente según sea necesario */
}
</style>
