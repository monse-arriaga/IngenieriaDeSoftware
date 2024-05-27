<template>
 <div>
    <TorneoCarta :tournament="tournamentO" />
  </div>
  <div class="brackets-viewer"></div>
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
import TorneoCarta from "./TorneoCarta.vue";
import TournamentService from "../services/TournamentService";
import { BracketsManager } from 'brackets-manager'
import { tournamentStorage } from "../store/tournament";


export default defineComponent({
  name: "Detalles",
  components: {
    TorneoCarta
  },
  setup() {
    const router = useRouter();
    const tournament = router.currentRoute.value.params.tournament;
    const userStore = useUserStore();
    const isLoggedIn = computed(() => userStore.isLoggedIn);
    const isEnrolled = ref(false);
    const tournamentO = ref("");
    const storage = new tournamentStorage();
    const manager = new BracketsManager(storage);

    

    const checkEnrolled = async () => {
        try {
          const enrolledTournaments = await UserService.tournaments_enrolled(authHeader().UserId);
          isEnrolled.value = enrolledTournaments.includes(tournament.toString());
        } catch (error) {
          isEnrolled.value = false;
        }
      };
    
    

    const getToournament = async () => {
        tournamentO.value = await TournamentService.getTournamentByName(tournament.toString());
    }

    async function render() {
      const data = await manager.get.tournamentData("3");
      console.log(data)
      window.bracketsViewer.render({
        stages: data.stage,
        matches: data.match,
        matchGames: data.match_game,
        participants: data.participant,
      });
    }

    onMounted(async () => {
        await getToournament();
        await manager.create.stage({
          tournamentId: 3,
          name: 'Elimination stage',
          type: 'double_elimination',
          seeding: ['Team 1', 'Team 2', 'Team 3', 'Team 4'],
          settings: { grandFinal: 'double' },
        });
        await render()
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
      tournamentO,
      isLoggedIn,
      enroll,
      render
    };
  },
});
</script>

<style lang="sass" >
.my-card 
    color: black
</style>
