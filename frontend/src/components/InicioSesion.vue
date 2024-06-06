<template>
    <div class="modal">
      <div class="modal-content">
      <!-- Contenido del formulario de inicio de sesión -->
      <button class="close-button" @click="cancelLogIn">x</button>
      <div class="mb-8 text-center">
        <h1 class="my-3 text-4xl font-bold text-white">Inicia Sesión</h1>
        <p class="text-sm text-white">Inicia sesión para acceder a tu cuenta</p>
      </div>
      <form @submit.prevent="submitForm">
        <!-- Campo de correo -->
        <div class="input-wrapper">
				<div>
					<label for="email" class="block mb-2 text-sm text-white">Nombre de usuario</label>
					<input type="text"  placeholder="Nombre de Usuario" v-model="userToLogIn.username" required class="w-full px-3 py-2 border rounded-md dark:border-gray-300 dark:bg-gray-50 dark:text-gray-800">
				</div>
        </div>
        <!-- Campo de contraseña -->
          <label for="password" class=" block mb-2 text-sm text-white">Contraseña</label>
         
          <input type="password" placeholder="Contraseña" v-model="userToLogIn.password" required class="w-full px-3 py-2 border rounded-md dark:border-gray-300 dark:bg-gray-50 dark:text-gray-800">
   
        <div v-if="showPopup" class="popup">
          <p>
          Contraseña o usuario equivocados.
          <br>
          Vuelva a intentar.  
          </p>
        </div>
        
        <button type="submit" style="margin: 15px;">Iniciar Sesión</button>
      </form>
      <p style="color: white;">¿No tienes una cuenta? <a href="#" @click="showRegister">Regístrate</a></p>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '../store/user'

export default defineComponent({
  name: "LoginUserView",
  setup() {
    const userToLogIn = ref({
      password: '',
      username: ''
    });
    const router = useRouter();
    const userStore = useUserStore();
    const showPopup = ref(false);

    if (userStore.isLoggedIn) {
       router.push({ name: "pagina-de-inicio" });
    }

    const submitForm = () => {
       userStore.login(userToLogIn.value.username, userToLogIn.value.password).then(() => {
        router.push({name: "pagina-de-inicio"});
      }).catch(() => {
        showPopup.value = true;
      });
      window.location.reload;
    };

    const cancelLogIn = () => {
      router.push({name: "pagina-de-inicio"});
    };

    const showRegister = () => {
      router.push({name: "registro"});
    };

    return {
      userToLogIn,
      cancelLogIn,
      submitForm,
      showRegister,
      showPopup
    };
  },
});
</script>

  
  <style scoped>
  .modal {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(4, 1, 19, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-content {
    background-color: rgb(36, 28, 46);
    padding: 20px;
    border-radius: 8px;
    position: relative;
  }
  
   .title {
    color: white; /* Cambia el color del texto a blanco */
  }
  
  .input-wrapper {
    margin-bottom: 10px; /* Espacio entre los campos de entrada */
  }
  
  .separator {
    margin: 20px 0; /* Espacio entre la fecha de nacimiento y los otros datos */
    border: none;
    border-top: 1px solid #ccc;
  }
   
  .close-button {
    position: absolute;
    top: -10px;
    right: -10px;
    background: none;
    border: none;
    color: white;
    font-size: 20px;
    cursor: pointer;
    z-index: 1;
  }
  
  .popup {
    background-color: red; 
    color: white;
    padding: 10px;
    margin-top: 20px; /* Adjust as needed */
  }  

  .close-button:hover {
    color: red;
  } 
  /* Ajusta los estilos según sea necesario */
  </style>
  
