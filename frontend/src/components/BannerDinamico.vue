<!-- Banner.vue -->
<template>
    <div class="banner" :style="{ backgroundColor: backgroundColor }">
        Carrusel dinamico

        <!-- Banner.vue
              <div class="carousel">
        <img src =../assets/ImagenesCarrusel/Imagen2.jpg />
      </div>
        !-->

    </div>
  </template>

<script lang="ts">
import { defineProps, ref, onMounted, onUnmounted } from 'vue';

const props = defineProps({
  images: Array,
  backgroundColor: String
});

const currentIndex = ref(0);
let intervalId: any;

const startCarousel = () => {
  intervalId = setInterval(() => {
    currentIndex.value = (currentIndex.value + 1) % props.images.length;
  }, 3000); // Cambia el intervalo de cambio de imagen según tus necesidades
};

const stopCarousel = () => {
  clearInterval(intervalId);
};

onMounted(() => {
  startCarousel();
});

onUnmounted(() => {
  stopCarousel();
});

export default {
  props: {
    images: Array,
    backgroundColor: String
  },
  methods: {
    getImagePath(_image: string) {
      return require(`../assets/ImagenesCarrusel/${_image}`);
    }
  }
};


</script>



<style scoped>
.banner {
  padding: 50px;
  color: white;
  text-align: center;
}

.carousel {
  position: relative;
  width: 100%;
  height: 200px; /* Ajusta la altura según sea necesario */
  overflow: hidden;
}

.carousel-slide {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover; /* Ajusta el comportamiento de las imágenes */
}
</style>