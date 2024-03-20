<template>
    <div class="modal">
      <div class="modal-content">
        <!-- Contenido del formulario de inicio de sesión -->
        <h2 class="title">Regístrate</h2>
        <form @submit.prevent="handleSubmit">
          <!-- Campos de entrada de usuario y contraseña -->
          <input type="text" placeholder="Nombre de usuario" v-model="username" required>
          <input type="password" placeholder="Contraseña" v-model="password" required>
          <br />
          <br />
        <!-- Input para la fecha de nacimiento -->
        <label style="color: white;"  for="fechaNacimiento">Fecha de nacimiento: </label>
        <input type="date" id="fechaNacimiento" v-model="fechaNacimiento" required>
            </form>
        <br />
        <br />
          <button type="submit">Regístrate</button>
          <br />
          <p style="color: white;">¿Ya tienes una cuenta? <a href="#">Inicia sesión</a></p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const username = ref('');
  const password = ref('');
  const fechaNacimiento = ref('');
  
  const handleSubmit = () => {
    //  lógica para enviar los datos de inicio de sesión al servidor

    // Luego, cierra el modal
    // Puedes emitir un evento o cambiar una propiedad para controlar la visibilidad del modal desde fuera
  };
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
  }

   .title {
    color: white; /* Cambia el color del texto a blanco */
  }

  .separator {
  margin: 20px 0; /* Espacio entre la fecha de nacimiento y los otros datos */
  border: none;
  border-top: 1px solid #ccc;
  }
    
  /* Ajusta los estilos según sea necesario */
  </style>
  