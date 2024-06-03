<template>
  <div>
    <q-splitter v-model="splitterModel">
      <template v-slot:before>
        <q-tabs v-model="tab" vertical class="text-teal">
          <q-tab name="Usuario" icon="mail" label="Usuario" />
          <q-tab name="Torneos" icon="alarm" label="Torneos" />
        </q-tabs>
      </template>

      <template v-slot:after>
        <q-tab-panels v-model="tab" animated dark swipeable vertical transition-prev="jump-up" transition-next="jump-up">
          <q-tab-panel name="Usuario">
            <div style="margin-top: 40px;"></div>
            <h4>Bienvenido, {{ userDet?.name }}</h4>
            <h4>Llena los campos que quieras cambiar.</h4>
            <div style="margin-top: 40px;"></div>
            <q-input filled clearable dark v-model="newUserName" :placeholder="userDet?.name" :dense="dense" />
            <div style="margin-top: 40px;"></div>
            <q-input filled clearable dark v-model="newUserMail" :placeholder="userDet?.email" :dense="dense" />
            <div style="margin-top: 40px;"></div>
            <q-input filled dark clearable autogrow v-model="newUserBio" :placeholder="userDet?.bio" :dense="dense" />
            <div style="margin-top: 40px;"></div>
            <q-btn color="secondary" label="Guardar Cambios" @click="updateUserDetails" />
          </q-tab-panel>

          <q-tab-panel name="Torneos">
            <div class="text-h4 q-mb-md">Torneos</div>
            <p>Selecciona el torneo que quieras modificar y haz los cambios que desees.</p>
            <q-select filled dark v-model="selectedTournament" :options="tournamentOptions" label="Selecciona un torneo" />
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
import User from '../types/User';
import TournamentService from '../services/TournamentService';

export default {
  setup() {
    const userStore = useUserStore();
    const userDet = ref<User | null>(null);
    const loading = ref(true);
    const tournamentOptions = ref([]);
    const selectedTournament = ref(null);
    const fetchUserDetails = async () => {
      try {
        const user = await UserService.find(userStore.user.id);
        userDet.value = user[0];
      } catch (error) {
        console.error("Error fetching user details:", error);
      } finally {
        loading.value = false;
      }
    };

    const fetchUserTournaments = async () => {
      try {
        const tournaments = await TournamentService.getUserTournaments(userStore.user.id);
        tournamentOptions.value = tournaments.map((t: any) => ({ label: t, value: t }));
      } catch (error) {
        console.error("Error fetching user tournaments:", error);
      }
    };
    
    onMounted(() => {
      console.log("Component mounted");
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
        await UserService.update(updatedUser, userStore.user.id);
        console.log("User updated successfully");
        // Optionally, refetch the user details to reflect changes
        fetchUserDetails();
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
