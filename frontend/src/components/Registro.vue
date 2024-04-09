<template>
  <div class="modal">
    <div class="modal-content">
        <!-- Contenido del formulario de inicio de sesión -->
        <button class="close-button" @click="cancelRegister">x</button>
        <h2 class="title">Empieza tu aventura ahora:</h2>
        <form @submit.prevent="submitForm">
          <!-- Campo de correo -->
          <div class="input-wrapper">
            <input type="text" placeholder="Correo" v-model="userToBeSaved.email" required>
          </div>
          <!-- Campo de nombre de usuario -->
          <div class="input-wrapper">
            <input type="text" placeholder="Nombre de usuario" v-model="userToBeSaved.name" required>
          </div>
          <!-- Campo de contraseña -->
          <div class="input-wrapper">
            <input type="password" placeholder="Contraseña" v-model="userToBeSaved.password" required>
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
import User from "../types/User";
import UserService from '../services/UserService';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: "RegisterUserView",
  setup() {
    const userToBeSaved = ref<User>({
      bio: "not-yet",
      bornDate: "1968-12-18",
      email: "",
      name: "",
      password: ""
    });
    const router = useRouter();
    const showPopup = ref(false);
    const submitForm = () => {
      UserService.save(userToBeSaved.value).then(() => {
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
