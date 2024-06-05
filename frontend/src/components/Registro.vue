<template>
  <div class="modal">
    <div class="modal-content">
        <!-- Contenido del formulario de inicio de sesión -->
        <button class="close-button" @click="cancelRegister">x</button>
        <div class="mb-8 text-center">
          <h1 class="my-3 text-4xl font-bold text-white">Regístrate</h1>
          <p class="text-sm text-white">¡Regístrate e inicia tu aventura!</p>
        </div>
        <form @submit.prevent="submitForm">
          <!-- Campo de correo -->
          <div class="input-wrapper">
            <label for="email" class="block mb-2 text-sm text-white">Dirección de correo</label>
            <input type="email" placeholder="Correo" v-model="userToBeSaved.email" required class="w-full md:w-96 px-3 py-2 border rounded-md dark:border-gray-300 dark:bg-gray-50 dark:text-gray-800">
          </div>
          <!-- Campo de nombre de usuario -->
          <div class="input-wrapper">
            <label for="email" class="block mb-2 text-sm text-white">Nombre de usuario</label>
            <input type="text" placeholder="Nombre de usuario" v-model="userToBeSaved.username" required class="w-full px-3 py-2 border rounded-md dark:border-gray-300 dark:bg-gray-50 dark:text-gray-800">
          </div>
          <!-- Campo de contraseña -->
          <div class="input-wrapper">
            <label for="email" class="block mb-2 text-sm text-white">Contraseña</label>
            <input type="password" placeholder="Contraseña" v-model="userToBeSaved.password" required class="w-full px-3 py-2 border rounded-md dark:border-gray-300 dark:bg-gray-50 dark:text-gray-800">
          </div>
          <div v-if="showPopup" class="popup">
            Ocurrio un error. Vuelva a intentar.
          </div>
          <button type="submit">Regístrate</button>
        </form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '../store/user';

export default defineComponent({
  name: "RegisterUserView",
  setup() {
    const userToBeSaved = ref<any>({
      bio: "not-yet",
      bornDate: "1968-12-18",
      email: "",
      username: "",
      password: ""
    });
    const router = useRouter();
    const userStore = useUserStore();
    const showPopup = ref(false);
    if (userStore.isLoggedIn) {
       router.push({ name: "pagina-de-inicio" });
    }

    const submitForm = () => {
      userStore.register(userToBeSaved.value).then(() => {
        userStore.login(userToBeSaved.value.username, userToBeSaved.value.password);
        router.push({name: "pagina-de-inicio"});
      }).catch(() => {
        showPopup.value = true;
      });
      window.location.reload;
    };
    const cancelRegister = () => {
      router.push({ name: "pagina-de-inicio"});
    };
    return {
      userToBeSaved,
      cancelRegister,
      submitForm,
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
  padding: 15px;
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

.close-button:hover {
  color: red;
} 

.popup {
  background-color: rgba(255, 0, 0, 0.8);
  color: white;
  padding: 10px;
  margin-top: 20px;
  margin-bottom: 10px;
  border-radius: 4px;
  text-align: center;
}  

.popup p {
  margin: 0;
}

/* Ajusta los estilos según sea necesario */
</style>
