<template>
    <div>
      <h1>Ganador</h1>
      <p>Torneo: {{ torneo }}</p>
      <p>Match: {{ match }}</p>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  
  export default defineComponent({
    name: 'DecidirGanador',
    computed: {
      torneo(): string | string[] {
        return this.$route.params.torneo;
      },
      match(): string | string[] {
        return this.$route.params.match;
      }
    }
  });
  </script>