<template>
    <div>
      <TorneoCarta :employee="employeeData" />
    </div>
  </template>
  
  <script>
  import TorneoCarta from './TorneoCarta.vue';
  
  export default {
    name: 'App',
    components: {
      TorneoCarta
    },
    data() {
      return {
        employeeData: {
          name: 'John Doe',
          bio: 'Lorem ipsum dolor sit amet',
          email: 'john.doe@example.com'
        }
      };
    }
  };
  </script>