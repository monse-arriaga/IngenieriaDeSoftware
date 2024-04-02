<template>
  <div class="modal">
    <div class="modal-content">
      <!-- Contenido del formulario de inicio de sesión -->
      <button class="close-button" @click="closeForm">x</button>
      <h2 class="title">Empieza tu aventura ahora:</h2>
      <form @submit.prevent="handleSubmit">
        <!-- Campo de correo -->
        <div class="input-wrapper">
          <input type="text" placeholder="Correo" v-model="email" required>
        </div>
        <!-- Campo de nombre de usuario -->
        <div class="input-wrapper">
          <input type="text" placeholder="Nombre de usuario" v-model="username" required>
        </div>
        <!-- Campo de contraseña -->
        <div class="input-wrapper">
          <input type="password" placeholder="Contraseña" v-model="password" required>
        </div>
        <button type="submit">Regístrate</button>
        <div v-if="popupMessage" class="popup">
          {{ popupMessage }}
        </div>
        <p style="color: white;">¿Ya tienes una cuenta? <a href="#" @click="showLogin">Inicia sesión</a></p>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';
import axios from 'axios';

const emits = defineEmits(['closeRegistro', 'showLogInInicio', 'registrado']);
const email = ref('');
const username = ref('');
const password = ref('');
const popupMessage = ref('');

const handleSubmit = async () => {
  try {
    const response = await axios.post('http://backend:8080/user/create/', {
      "bio": "not yet",
      "bornDate": "1968-12-18",
      "email": email.value,
      "name": username.value,
      "password": password.value
    });
    emits('registrado')
  } catch (error) {
    popupMessage.value = "Error al registrar usuario";
  }
};

const closeForm = () => {
  emits('closeRegistro');
}

const showLogin = () => {
  emits('showLogInInicio');
}
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
  background-color: red; 
  color: white;
  padding: 10px;
  margin-top: 20px; /* Adjust as needed */
}

/* Ajusta los estilos según sea necesario */
</style>
