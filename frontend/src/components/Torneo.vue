<template>
  <div class="tournament-info">
    <div v-if="loading">
      <h1>Loading...</h1>
    </div>

    <div v-else-if="tournamentDetails">
      <q-img class="col-2"
      :src="tournamentDetails.image"
      style="width: 1250px; height: 700px; object-fit: cover;"/>

      <q-tabs class="text-blue" active-color="bg-indigo-6" v-model="tab">
        <q-tab name="details" label="Detalles del Torneo" />
        <q-tab name="brackets" label="Clasificatorias" />
      </q-tabs>
      <q-tab-panels v-model="tab" dark  >
        <q-tab-panel name="details">
          <div class="tournament-info">
            <h6 class="text-grey-8 q-gutter-md;">
              <q-icon name="lock_open" />{{ '   '+tournamentDetails.state}}  
              <q-icon name="sports_esports" />{{ '   '+tournamentDetails.tournamentGame}}  
              <q-icon name="sports_kabaddi" />{{ '   '+tournamentDetails.tournamentType}}
            </h6>
            <h1 class="text-white q-gutter-md;">{{ tournamentDetails.name }}</h1>
            <h4 class="text-grey-6 q-gutter-md;">{{ tournamentDetails.description }}</h4>
            <div class="tournament-info">
              <h6 class="text-grey-5 q-gutter-md;">
                <q-icon name="today" />{{ '   '+tournamentDetails.date}}  
                <q-icon name="schedule" />{{ '   '+tournamentDetails.time}}  
                <q-icon name="groups" />{{ '   '+tournamentDetails.inPlayers+'/'+tournamentDetails.players}}
              </h6>
            <p></p>
            </div>
          </div>
        </q-tab-panel>

        <q-tab-panel name="brackets">
          <div class="brackets-viewer" id="example" style="width: 100%; height: 700px; background-color: #2c3e50;">
          </div>
        </q-tab-panel>
      </q-tab-panels>
    </div>

    <div v-else>
      <h1>No carga lol</h1>
    </div>

    <div class="tournament-info">
      <div v-if="isLoggedIn">
        <q-btn v-if="!isEnrolled" id="plus-button" color="primary" @click="enroll">
          <strong>Inscribete</strong> 
        </q-btn>   
        <q-btn v-else id="plus-button" color="primary">
          <strong>Ya estás inscrito</strong> 
        </q-btn>         
      </div>
      <div v-else>
        Inicia Sesión Por Favor
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref, watch } from "vue";
import { useRouter } from "vue-router";
import UserService from "../services/UserService";
import authHeader from "../services/auth-header";
import { useUserStore } from "../store/user";
import TorneoCarta from "./TorneoCarta.vue";
import TournamentService from "../services/TournamentService";
import { BracketsManager } from 'brackets-manager';
import { tournamentStorage } from "../store/tournament";
import Tournament from "../types/Tournament";
import ParticipantService from "../services/ParticipantService";
import { Match, Participant } from "brackets-model";

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
    const tab = ref('details'); // Add ref for the tab
    const storage = new tournamentStorage();
    const manager = new BracketsManager(storage);

    const checkEnrolled = async () => {
      try {
        const enrolledTournaments = await UserService.tournaments_enrolled(authHeader().UserId);
        isEnrolled.value = enrolledTournaments.includes(tournamentName);
      } catch (error) {
      }
    };

    const fetchTournamentDetails = async () => {
      try {
        const tournament = await TournamentService.getTournamentByName(tournamentName);
        tournamentDetails.value = tournament[0];
      } catch (error) {
        console.error("Error fetching tournament details:", error);
      } finally {
        loading.value = false; // Set loading to false regardless of success or failure
      }
    };

    async function renderBrackets() {
      const data = await manager.get.tournamentData(tournamentName);
      console.log(tournamentName);
      window.bracketsViewer.render({
        stages: data.stage,
        matches: data.match,
        matchGames: data.match_game,
        participants: data.participant,
      }, {
          selector: '#example',
          participantOriginPlacement: 'before',
          highlightParticipantOnHover: true,
          onMatchClick: onMatchClick
        });
    }   

    const onMatchClick = async (match1: Match) => {
      if(await userOwnsTournament()){
        const match:number = match1.id as number
        const torneo = tournamentName
        router.push({ name: 'ganador', params: {
          torneo,  match
      }})
   
      }
    }
    
    const userOwnsTournament = async () => {
      const owning:any[] = await UserService.tournaments_owned(authHeader().UserId)
      if(owning.filter(tournament => tournament == tournamentName).length > 0) {
        return true
      } else {
        return false
      }
    }

    onMounted(async () => {
      await checkEnrolled();
      await fetchTournamentDetails();

      // Render brackets if the "brackets" tab is already active on mount
      if (tab.value === 'brackets') {
        await renderBrackets();
      }
    });

    // Watch for changes in the tab and call renderBrackets when "brackets" tab is selected
    watch(tab, async (newTab) => {
      if (newTab === 'brackets') {
        await renderBrackets();
      }
    });

    const enroll = async () => {
      try {
        const participants:Participant[] = await ParticipantService.select({
          tournament_id: tournamentName}) as Participant[];
        const participant = participants.filter(participante =>
        ("Player " + tournamentName + " " + (tournamentDetails.value?.inPlayers as number)) == participante.name);
        await ParticipantService.update(participant[0])
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
      tab, // Return the tab ref
      renderBrackets, // Return the render function
    };
  },
});
</script>

<style scoped>
.tournament-info {
  margin-top: 55px; /* Adjust the margin-top as needed */
}
</style>
