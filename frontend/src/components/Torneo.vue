<template>
  <div v-if="isLoggedIn">
    <q-btn v-if="!isEnrolled" id="plus-button" color="primary" @click="enroll">
      <strong>Inscribete</strong> 
    </q-btn>   
    <q-btn v-else id="plus-button" color="primary" >
      <strong>Ya estás inscrito</strong> 
    </q-btn>         
  </div>
  <div v-else>
    Inicia Sesión Por Favor
  </div>
</template>
  
<script lang="ts">
import { computed, defineComponent, onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import UserService from "../services/UserService";
import authHeader from "../services/auth-header";
import { useUserStore } from "../store/user";

export default defineComponent({
  name: "Detalles",
  setup() {
    const router = useRouter();
    const tournament = router.currentRoute.value.params.tournament;
    const userStore = useUserStore();
    const isLoggedIn = computed(() => userStore.isLoggedIn);
    const isEnrolled = ref(false);
    const checkEnrolled = async () => {
      try {
        const enrolledTournaments = await UserService.tournaments_enrolled(authHeader().UserId);
        isEnrolled.value = enrolledTournaments.includes(tournament.toString());
      } catch (error) {
        isEnrolled.value = false;
      }
    };
    onMounted(async () => {
      await checkEnrolled();
    });
    const enroll = async () => {
      try {
        await UserService.enroll(authHeader().UserName, tournament.toString());
        window.location.reload();
      } catch (error) {
        console.error("Error enrolling in tournament:", error);
      }
    };

    return {
      isEnrolled,
      checkEnrolled,
      isLoggedIn,
      enroll,
    };
  },
});
</script>

<style lang="sass" >
.my-card 
    color: black
</style>
