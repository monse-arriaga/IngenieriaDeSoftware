<!--npm install vue-router @types/vue-router-->


<template>
  <div class = "container">
  
    <div class="corner-buttons">
     
      <div v-if="isLoggedIn" @click="() => goToPage('/configuracion')"  class="corner-button">
        <img src="./assets/ImagenesMenuPrincipal/configuracion.png" alt="Botón 3" class="button-image" />
      </div>

      <!-- Espacio entre botones -->
      <div style="margin-bottom: 10px;"></div> 

      <!-- Boton quasar -->
      <q-btn v-if="isLoggedIn" id="plus-button" color="primary" @click="() => goToPage('/crear-torneo')">
        <strong>+</strong> 
        <q-tooltip anchor="center right" self="center left" :offset="[10, 10]">
          <strong>¡Crea tu propio torneo!</strong> 
        </q-tooltip>
      </q-btn>      

      </div>

      <!-- Imagen en la esquina derecha -->
      <div @click="() => goToPage('/')"  class="corner-button">
        <img src="./assets/ImagenesMenuPrincipal/playbit.png" alt="Botón 2" class="right-corner-image" />
      </div>
     

       <!-- Boton de busqueda, te lleva a la pagina de busqueda y mantiene el input-->
       <div class="search-bar">
        <input v-model="searchVal" class="search-input" type="text" placeholder="Busca un torneo" @keyup.enter="handleSearch"/>
        <button class="search-button" @click="handleSearch">
          <i class="fas fa-search"></i>
        </button>
      </div>
      
  
      <button v-if="!isLoggedIn" @click="() => goToPage('/iniciar-sesion')" class="login-button">
        Iniciar Sesión
      </button>
      <button v-else @click="logout" class="login-button">
        Cerrar Sesión
      </button>
  


    <router-view /> <!-- Esto es importante para que vue-router renderice los componentes correspondientes -->
  
    </div>
  
  
  </template>
  
  <script setup lang="ts">
  import { useRouter } from 'vue-router';
  import { computed } from 'vue';
  import { useUserStore } from './store/user';
  import { ref } from 'vue'; 
  import 'vue-search-input/dist/styles.css';
  
  
  const router = useRouter();

  const goToPage = (route: string) => {
    router.push({ path: route });
  };
  
  const userStore = useUserStore();
  const isLoggedIn = computed(() => userStore.isLoggedIn);
  const logout = () => {
    userStore.signOut(); 
    goToPage('/');
  };


  const handleSearch = () => {
    // Aquí puedes poner la lógica para manejar la búsqueda
    router.push({ path: '/busqueda-torneos', query: { q: searchVal.value }});
  };

  const searchVal = ref('')
 

  </script>
  
  
  <style scoped>
  .search-input {
    width: 300px;
    padding: 10px;
    border: none;
    outline: none;
    border-radius: 20px;
    background-color: white !important;
    font-size: 16px;
    color: rgb(2, 0, 0); /* Color del texto */
  }
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
  .container {
    display: flex;
    flex-direction: column;
    align-items: center; /* Centra horizontalmente */
  }

  .right-corner-image {
    position: fixed; /* Posicionamiento fijo */
    height: 100px; 
    width: 150px; 
    top: 20px; /* Distancia desde la parte superior */
    left: 25px; /* Distancia desde la derecha */
  }
  
  .corner-buttons {
    position: fixed; /* Posicionamiento fijo */
    top: 85px; /* Distancia desde la parte superior */
    right: 25px; /* Distancia desde la derecha */
  }
  
  .corner-button {
    cursor: pointer; /* Cambia el cursor al pasar sobre los botones */
  }
  
  .button-image {
    width: 40px; /* Ajusta el ancho de la imagen */
    height: auto; /* Permite que la altura se ajuste automáticamente para mantener la proporción */
  
  }

  .search-bar {
    position: absolute; /* Posicionamiento absoluto */
    top: 20px; /* Ajusta la distancia desde la parte superior según sea necesario */
    left: 50%; /* Centra horizontalmente */
    transform: translateX(-50%); /* Centra horizontalmente */
  }
  
  .search-input {
    width: 650px; /* Ancho deseado del input de búsqueda */
    padding: 10px; /* Ajusta el relleno del input */
    border: none; /* Quita el borde del input */
    outline: none; /* Quita el contorno alrededor del input al hacer clic */
    border-radius: 20px; /* Hace que el input tenga forma circular */
    background-color: #fdf7f7 !important;  /* Color de fondo del input */
    font-size: 16px; /* Tamaño de fuente del input */
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

  #plus-button {
    font-size: 16px; /* Tamaño de fuente más grande */
  }

  .search-button {
    position: absolute;
    top: 2px;
    right: 6px; /* Ajusta la posición del botón según sea necesario */
    background-color: #27e6b900;
    border: none;
    cursor: pointer;
  }
  
  .search-button i {
    color: #111111; /* Color del ícono de lupa */
    font-size: 20px; /* Tamaño del ícono de lupa */
  }

  </style>./components/Registro.vue