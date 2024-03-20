<!--npm install vue-router @types/vue-router-->


<template>
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
  
    <button @click="showLoginRegistro" class="login-button">
        {{status}}
    </button>
  
    <Registro v-if="showRegistro" @registrado="registrado"  @closeRegistro="closeRegistro" @showLogInInicio="showLogInInicio"/>
    <InicioSesion v-if="showInicio" @closeInicio="closeInicio" @sesionIniciada="sesionIniciada"/>
  
      <router-view /> <!-- Esto es importante para que vue-router renderice los componentes correspondientes -->
  
    </div>
  
  
  </template>
  
  <script setup lang="ts">
  import { useRouter } from 'vue-router';
  import { ref } from 'vue';
  import Registro from './components/Registro.vue';
  import InicioSesion from './components/InicioSesion.vue';
  
  const router = useRouter();

  const goToPage = (route: string) => {
    router.push({ path: route });
  };
  
  const showRegistro = ref(false);
  const showInicio = ref(false);
  const status = ref('Registro')

  const showLoginRegistro = () => {
    showRegistro.value = true;
  };

  const closeRegistro = () => {
    showRegistro.value = false;
  };

  const showLogInInicio = () => {
    showRegistro.value = false;
    showInicio.value = true;
  }

  const closeInicio = () => {
    showInicio.value = false;
  }

  const sesionIniciada = () => {
    showRegistro.value = false;
    showInicio.value = false;
    status.value = "Salir"

  }

  const registrado = () => {
    showRegistro.value = false;
    status.value = "Salir"
  }
  
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
    filter: drop-shadow(0 0 2em #4f34c7aa);
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
  
  .login-button {
    position: absolute; /* Posicionamiento absoluto */
    top: 20px; /* Distancia desde la parte superior */
    right: 20px; /* Distancia desde la derecha */
    cursor: pointer; /* Cambia el cursor al pasar sobre los botones */
    padding: 8px 16px; /* Añade un poco de espacio alrededor del texto */
    border: 1px solid #2e0458; /* Agrega un borde */
    border-radius: 4px; /* Añade bordes redondeados */
    background-color: #27e6ba; /* Cambia el color de fondo */
  }
  
  .pagina-inicio {
    margin-top: 60px; /* Espacio entre el menú y el contenido de la página */
  }
  </style>./components/Registro.vue