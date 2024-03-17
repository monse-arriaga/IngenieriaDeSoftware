<!--npm install vue-router @types/vue-router-->


<template>
<PaginaInicio msg="Monse es cool xD" />
<div class = "container">
  <div class="corner-buttons">
    <div @click="() => goToPage('/')"  class="corner-button">
      <img src="./assets/ImagenesMenuPrincipal/Home.png"alt="Botón 1" class="button-image" />
    </div>

    <!-- Espacio entre botones -->
    <div style="margin-bottom: 10px;"></div> 

    <div @click="() => goToPage('./BusquedaTorneos')"  class="corner-button">
      <img src="./assets/ImagenesMenuPrincipal/Trofeo.png" alt="Botón 2" class="button-image" />
    </div>

    <!-- Espacio entre botones -->
    <div style="margin-bottom: 10px;"></div> 

    <div @click="() => goToPage('./BusquedaTorneos')"  class="corner-button">
      <img src="./assets/ImagenesMenuPrincipal/configuracion.png" alt="Botón 3" class="button-image" />
    </div>
  </div>

    <router-view /> <!-- Esto es importante para que vue-router renderice los componentes correspondientes -->

        <!-- Contenido estático de la página de inicio -->
    <div class="pagina-inicio">
      <PaginaInicio msg="Monse es cool xD" />
    </div>
  </div>


</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

const goToPage = (route: string) => {
  router.push({ path: route });
};
</script>


<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>

<style scoped>

.container {
  display: flex;
  flex-direction: column;
  align-items: center; /* Centra horizontalmente */
}

.corner-buttons {
  position: fixed; /* Posicionamiento fijo */
  top: 180px; /* Distancia desde la parte superior */
  left: 20px; /* Distancia desde la derecha */
}

.corner-button {
  cursor: pointer; /* Cambia el cursor al pasar sobre los botones */
}

.button-image {
  width: 40px; /* Ajusta el ancho de la imagen */
  height: auto; /* Permite que la altura se ajuste automáticamente para mantener la proporción */
}
</style>
