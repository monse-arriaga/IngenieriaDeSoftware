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
  <div v-if="loading">
    <h1>Loading...</h1>
  </div>
  <div v-else-if="tournamentDetails">
    <h1>{{ tournamentDetails.name }}</h1>
    <p>{{ tournamentDetails.prize }}</p>
    <!-- Render other tournament details here -->
  </div>
  <div v-else>
    <h1>No carga lol</h1>
  </div>
</template>
  
<script lang="ts">
import { computed, defineComponent, onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import UserService from "../services/UserService";
import authHeader from "../services/auth-header";
import { useUserStore } from "../store/user";
import TorneoCarta from "./TorneoCarta.vue";
import TournamentService from "../services/TournamentService";
import Tournament from "../types/Tournament";


export default defineComponent({
  name: "Detalles",
  components: {
    TorneoCarta
  },
  setup() {
  const router = useRouter();
  const tournamentName = router.currentRoute.value.params.tournament.toString();
  const userStore = useUserStore();
  const isLoggedIn = computed(() => userStore.isLoggedIn);
  const isEnrolled = ref(false);
  const tournamentDetails = ref<Tournament | null>(null);
  const loading = ref(true); // Add loading state

  const checkEnrolled = async () => {
    try {
      const enrolledTournaments = await UserService.tournaments_enrolled(authHeader().UserId);
      isEnrolled.value = enrolledTournaments.includes(tournamentName);
    } catch (error) {
      console.error("Error checking enrollment:", error);
    }
  };

  const fetchTournamentDetails = async () => {
    try {
      const tournament = await TournamentService.getTournamentByName(tournamentName);
      tournamentDetails.value = tournament[0];
    } catch (error) {
      console.error("Error fetching tournament details:", error);
    }finally {
        loading.value = false; // Set loading to false regardless of success or failure
      }
  };

  onMounted(async () => {
    await checkEnrolled();
    await fetchTournamentDetails();
  });

  const enroll = async () => {
    try {
      await UserService.enroll(authHeader().UserName, tournamentName);
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
    tournamentDetails,
    loading,
  };
},
});
</script>

<style lang="sass" >
.my-card 
    color: black
</style>