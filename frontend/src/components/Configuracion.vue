<template>
  <div>
    <q-splitter v-model="splitterModel">
      <template v-slot:before>
        <q-tabs v-model="tab" vertical class="text-teal">
          <q-tab name="Usuario" icon="person" label="Usuario" />
          <q-tab name="Torneos" icon="emoji_events" label="Torneos" />
        </q-tabs>
      </template>

      <template v-slot:after>
        <q-tab-panels v-model="tab" animated dark swipeable vertical transition-prev="jump-up" transition-next="jump-up">
          <q-tab-panel name="Usuario">
            <div style="margin-top: 40px;"></div>
            <h4>Bienvenido, {{ userDet?.name }}</h4>
            <h4>Llena los campos que quieras cambiar.</h4>
            <div style="margin-top: 40px;"></div>
            <q-input filled clearable dark v-model="newUserName" :placeholder="userDet?.name" label="Nombre del usuario" :dense="dense" />
            <div style="margin-top: 40px;"></div>
            <q-input filled clearable dark v-model="newUserMail" :placeholder="userDet?.email"  label="Email del usuario" :dense="dense" />
            <div style="margin-top: 40px;"></div>
            <q-input filled dark clearable autogrow v-model="newUserBio" :placeholder="userDet?.bio"  label="Biografía del usuario" :dense="dense" />
            <div style="margin-top: 40px;"></div>
            <q-btn color="secondary" label="Guardar Cambios" @click="updateUserDetails" />
          </q-tab-panel>

          <q-tab-panel name="Torneos">
            <div class="text-h4 q-mb-md">Torneos</div>
            <p>Selecciona el torneo que quieras modificar y haz los cambios que desees.</p>
            <q-select filled dark v-model="selectedTournament" :options="tournamentOptions" label="Selecciona un torneo" @update:model-value="fetchTournamentDetails" />
            <div v-if="selectedTournamentDetails">
              <div style="margin-top: 40px;"></div>
              <q-input filled clearable autogrow dark v-model="newTournamentDescription" :placeholder="selectedTournamentDetails.description" label="Descripción" />
              <div style="margin-top: 40px;"></div>
              <q-input filled clearable dark v-model="newTournamentDate" type="date" :placeholder="selectedTournamentDetails.date" label="Fecha" />
              <div style="margin-top: 40px;"></div>
              <q-input filled clearable dark v-model="newTournamentTime" type="time" :placeholder="selectedTournamentDetails.time" label="Hora" />
              <div style="margin-top: 40px;"></div>
              <q-btn color="secondary" label="Guardar Cambios" @click="updateTournamentDetails" />
            </div>
          </q-tab-panel>
        </q-tab-panels>
      </template>
    </q-splitter>
  </div>
</template>

<script lang="ts">
import { ref, onMounted } from 'vue';
import { useUserStore } from '../store/user';
import UserService from '../services/UserService';
import TournamentService from '../services/TournamentService';
import User from '../types/User';
import Tournament from '../types/Tournament';

export default {
  setup() {
    const userStore = useUserStore();
    const userDet = ref<User | null>(null);
    const loading = ref(true);
    const tournamentOptions = ref([]);
    const selectedTournament = ref(null );
    const selectedTournamentDetails = ref<Tournament | null>(null);
    const newTournamentName = ref('');
    const newTournamentDescription = ref('');
    const newTournamentDate = ref('');
    const newTournamentTime = ref('');
    const id = userStore.user?.id != undefined ? userStore.user.id : 0

    const fetchUserDetails = async () => {
      try {
        const user = await UserService.find(id);
        userDet.value = user[0];
      } catch (error) {
        console.error("Error fetching user details:", error);
      } finally {
        loading.value = false;
      }
    };

    const fetchUserTournaments = async () => {
      try {
        const tournaments = await TournamentService.getUserTournaments(id);
         tournamentOptions.value = tournaments.map((t: any) => ({ label: t, value: t }));
      
      } catch (error) {
        console.error("Error fetching user tournaments:", error);
      }
    };

    const fetchTournamentDetails = async (value?: any) => {
      console.log("fetchTournamentDetails called with value:", value);
      if (selectedTournament.value) {
        console.log(selectedTournament.value);
        try {
          const wrapper = selectedTournament.value as any
          const tournament = await TournamentService.getTournamentByName(wrapper.label);
          console.log("Fetched tournament details:", tournament);
          selectedTournamentDetails.value = tournament[0];
          newTournamentName.value = tournament[0].name;
        } catch (error) {
          console.error("Error fetching tournament details:", error);
        }
      }
    };

    const updateTournamentDetails = async () => {
      if (selectedTournamentDetails.value) {
        try {
          const updatedTournament = {
            name: newTournamentName.value || selectedTournamentDetails.value.name,
            description: newTournamentDescription.value || selectedTournamentDetails.value.description,
            date: newTournamentDate.value || selectedTournamentDetails.value.date,
            time: newTournamentTime.value || selectedTournamentDetails.value.time,
            players: selectedTournamentDetails.value.players,
            image: selectedTournamentDetails.value.image,
            state: selectedTournamentDetails.value.state,
            tournamentType: selectedTournamentDetails.value.tournamentType,
            tournamentGame: selectedTournamentDetails.value.tournamentGame,
            playersBT: selectedTournamentDetails.value.playersBT,
            inPlayers: selectedTournamentDetails.value.inPlayers,
            prize: selectedTournamentDetails.value.prize,
          };
          await TournamentService.updateTournament(updatedTournament);
          console.log("Tournament updated successfully");
          // Optionally, refetch the tournament details to reflect changes
          fetchTournamentDetails();
          location.reload();
        } catch (error) {
          console.error("Error updating tournament details:", error);
        }
      }
    };

    onMounted(() => {
      fetchUserDetails();
      fetchUserTournaments();
    });

    const newUserName = ref('');
    const newUserMail = ref('');
    const newUserBio = ref('');

    const updateUserDetails = async () => {
      try {
        const updatedUser = {
          id: userDet.value?.id,
          name: newUserName.value || userDet.value?.name,
          password: userDet.value?.password,
          bornDate: userDet.value?.bornDate,
          email: newUserMail.value || userDet.value?.email,
          bio: newUserBio.value || userDet.value?.bio,
        };
        await UserService.update(updatedUser, id);
        console.log("User updated successfully");
        // Optionally, refetch the user details to reflect changes
        fetchUserDetails();
        location.reload();
      } catch (error) {
        console.error("Error updating user details:", error);
      }
    };

    return {
      tab: ref('Usuario'),
      splitterModel: ref(20),
      userDet,
      newUserName,
      newUserMail,
      newUserBio,
      dense: ref(false),
      updateUserDetails,
      selectedTournament,
      selectedTournamentDetails,
      newTournamentName,
      newTournamentDescription,
      newTournamentDate,
      newTournamentTime,
      fetchTournamentDetails,
      updateTournamentDetails,
      tournamentOptions,
    };
  }
}
</script>


<style scoped>
.q-splitter {
  margin-top: 75px; /* Baja el splitter */
  height: 4000000px; /* Ajusta la altura del splitter */
}
</style>
