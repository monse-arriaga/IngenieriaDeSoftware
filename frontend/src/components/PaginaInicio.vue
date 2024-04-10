<template>
    <div id="app">
    <BannerDinamico :images="bannerImages" :backgroundColor="bannerBackgroundColor" />
    </div>
</template>

<script lang="ts">

import BannerDinamico from '../components/BannerDinamico.vue';

export default {
  name: 'App',
  components: {
    BannerDinamico 
  },
  data() {
    return {
      bannerImages: ['Imagen1.jpg','Imagen2.jpg'],
      bannerBackgroundColor: 'blue'
    };
  }
};
</script>

<style scoped>
  #app {
    text-align: center;
  }

  /* Estilos para el banner */
  .banner {
  background-color: #e3e6ea00;
  color: #faf4f4;
  padding: 125px 0; /* Ajusta el padding para controlar el tamaño verticalmente */
  margin-top: 0; /* Elimina el margen superior */
  margin-bottom: 10px; /* Ajusta el margen inferior mínimo */
  position: fixed; /* Fija la posición del banner */
  top: 80px; /* Posición en la parte superior de la ventana */
  left: 90px; /* Alinea el banner al borde izquierdo */
  width: calc(100% - 250px); /* Calcula el ancho del banner para dejar un espacio de 90px en el borde derecho */
  }
</style>