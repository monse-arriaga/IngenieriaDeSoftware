<template>
    <div class="q-pa-md">
      <q-stepper
        v-model="step"
        ref="stepper"
        dark
        color="#2e0458" 
        animated
      >
        <q-step
          :name="1"
          title="Definamos tu torneo"
          icon="settings"
          :done="step > 1"
        >
          Antes de crear tu torneo, hay unos datos que necesitamos saber

          <div style="margin-top: 20px;"></div>
             
          <q-input dark filled label-color="white" v-model="tournamentTBC.name" label="Nombre del torneo" :input-style="{ color: 'white' }" />
          
          <div style="margin-top: 20px;"></div>

          <div class="input-container">
            <!-- Input para la fecha del torneo -->
            <q-input dark filled label-color="white" v-model="tournamentTBC.date" label="Fecha del torneo" type="date" :input-style="{ color: 'white'}" style="max-width: 3000px" />
            
            <!-- Espacio entre los inputs -->
            <div style="width: 20px;"></div>
            
            <!-- Input para la hora del torneo -->
            <q-input dark filled label-color="white" v-model="tournamentTBC.time" label="Hora del torneo" type="time" :input-style="{ color: 'white'}" style="max-width: 3000px" />
          </div>

        <!-- Selector de opciones múltiples -->
        <div style="margin-top: 40px;"></div>
        <div class="q-gutter-md">
        <q-select dark filled v-model="tournamentTBC.tournamentType" :options="options" label="Selecciona el tipo de torneo" />
        </div>

        <div style="margin-top: 40px;"></div>
        <div class="input-container">
        <q-input dark filled v-model.number="tournamentTBC.players" type="number" label="Personas por torneo"  style="max-width: 350px"
        />

        <!-- Espacio entre los inputs -->
        <div style="width: 20px;"></div>

        <q-input dark filled v-model.number="tournamentTBC.playersBT" type="number" label="Personas por equipo"  style="max-width: 350px"/>
        </div>

        <div style="margin-top: 40px;"></div>
        <q-input dark
        v-model="tournamentTBC.description"
        filled
        clearable
        autogrow
        label="Da una breve descripción de tu torneo para los futuros participantes"
        />

     
        </q-step>
  
        <!--
        <q-step
          :name="2"
          title="Invitemos a algunos amigos"
          icon="group_add"
          :done="step > 2"
        >
          Escribe el correo o nombre de usuario de los amigos que te gustaría que sean parte de tu torneo.

        </q-step>
  
        <q-step
          :name="3"
          title="Revisemos nuestras reglas"
          icon="add_comment"
        >
        Antes de comenzar la aventura, hay unas reglas que como organizador del torneo, tienes que cumplir
        </q-step>
        -->
  
        <template v-slot:navigation>
          <q-stepper-navigation>
            <q-btn @click="submit" color="primary" :label="step === 1 ? 'Finish' : 'Continue'" />
            <q-btn v-if="step > 1" flat color="primary" @click="onPrevious" label="Back" class="q-ml-sm" />
          </q-stepper-navigation>
        </template>
      </q-stepper>
    </div>
  </template>
  
  <script lang="ts">
  import { Ref, ref, defineComponent } from 'vue';
  import { QStepper } from 'quasar'; 
  import TournamentService from '../services/TournamentService';
  import Tournament from '../types/Tournament';
  import { useRouter } from 'vue-router';

  export default defineComponent({
    setup () {
      const stepper = ref() as Ref<QStepper>
      const router = useRouter();
      const onNext = () => {
        if (stepper.value) {
          stepper.value.next();
        }
      };
      const onPrevious = () => {
        if (stepper.value) {
          stepper.value.previous();
        }
      };
      const tournamentTBC = ref<Tournament>({
        name: "MyTorunament",
        players: 2, 
        description: " ",
        state: "abierto",
        tournamentType: "Eliminación Directa",
        inPlayers: 0,
        date: "1-1-2000",
        prize: 0,
        time: "0:0 AM",
        playersBT: 1
      });

      const submit = () => {
        TournamentService.tournament(tournamentTBC.value).then(() => {
          router.push({name: "pagina-de-inicio"});
        });
        window.location.reload;
      };
      return {
        step: ref(1),
        nombre: ref(''), // Variable para almacenar el nombre del torneo
        fecha: ref(''),
        hora: ref(''),
        informacion: ref(''),
        noJugadores: ref(10),
        jugadoresXequipo: ref(10),
        tipoTorneo: ref(null), // Variable para almacenar la opción seleccionada del selector
        options: [
        'Torneo de Liga', 'Liga y Eliminatoria', 'Eliminatoria'],
        stepper,
        onNext,
        onPrevious,
        submit,
        tournamentTBC,
      }
    }
  })
  </script>

<style scoped>
.input-container {
  display: flex;
  align-items: center;
}
</style>