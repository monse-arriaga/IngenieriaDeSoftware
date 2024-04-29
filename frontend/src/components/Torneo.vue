<template>
  <q-btn v-if="!isEnrolled" id="plus-button" color="primary" @click="enrroll">
    <strong>Inscribete</strong> 
  </q-btn>   
  <q-btn v-else id="plus-button" color="primary" >
    <strong>Ya estás inscrito</strong> 
  </q-btn>   
  
</template>
  
<script lang="ts">
import { defineComponent} from "vue";
import { useRouter } from "vue-router";
import UserService from "../services/UserService";
import authHeader from "../services/auth-header";
//import Tournament from "../types/Tournament";

export default defineComponent({
  name: "Detalles",
  setup() {
    const router = useRouter();
    let isEnrolled = false;
    const  tournament =  router.currentRoute.value.params.tournament;
    const enrroll = () => {
        UserService.enroll(authHeader().UserId, tournament).then(() => {
          isEnrolled = true;
        });
        window.location.reload;
    };
    return {
      isEnrolled,
      enrroll,
    };
  },
});
</script>

<style lang="sass" >
.my-card 
    color: black
</style>