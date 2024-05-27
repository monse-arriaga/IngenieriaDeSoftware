<template>
    <div class="q-pa-md" style="margin-top: 50px;">
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
        <q-select dark filled v-model="tournamentTBC.tournamentType" :options="optionsTipo" label="Selecciona el tipo de torneo" />
        </div>

        <!-- Selector de opciones múltiples -->
        <div style="margin-top: 40px;"></div>
        <div class="q-gutter-md">
        <q-select dark filled v-model="tournamentTBC.tournamentGame" :options="optionsGame" label="Selecciona el juego" />
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

        <q-stepper-navigation>
          <q-btn @click="() => { done1 = true; step = 2 }" color="primary" label="Continue" />
        </q-stepper-navigation>
     
        </q-step>

  
        <q-step
          :name="2"
          title="Selecciona la imagen que tendrá tu torneo"
          icon="group_add"
          :done="step > 2"
        >
          Ahora es momento de que agreguemos la imagen que representará tu torneo. <p></p>
          1. Escogela en un navegador web, dandole copiar y pegar<p></p>
          2. Da pegar en el siguiente link: https://es.imgbb.com/<p></p>
          3. Pegala aquí <p></p>
          <q-input dark filled label-color="white" v-model="tournamentTBC.image" label="Imagen de tu torneo" :input-style="{ color: 'white' }" />

        </q-step>
  
           <!--
        <q-step
          :name="3"
          title="Invitemos a algunos amigos"
          icon="group_add"
        >
        Antes de comenzar la aventura, hay unas reglas que como organizador del torneo, tienes que cumplir
        </q-step>
        -->
  
        <template v-slot:navigation>
          <q-stepper-navigation>
            <!-- <q-btn color="primary" @click="{done1 = true; step=3}" label="Finish" /> -->
            <q-btn color="primary" @click="submit" :label="step === 2 ? 'Finish' : 'boton'"  />
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
      const step = ref(1)
      const done1 = ref(false)
      const done2 = ref(false)
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
        tournamentGame: "Minecraft",
        tournamentType: "Eliminación Directa",
        inPlayers: 0,
        date: "1-1-2000",
        prize: 0,
        time: "0:0 AM",
        playersBT: 1,
        image: "https://i.ibb.co/kXPjvqW/image.png"
      });

      const submit = () => {
        TournamentService.tournament(tournamentTBC.value).then(() => {
          router.push({name: "pagina-de-inicio"});
        });
        window.location.reload;
      };
      return {
        step: ref(1),
        done1,
        done2,
        nombre: ref(''), // Variable para almacenar el nombre del torneo
        fecha: ref(''),
        hora: ref(''),
        imagen: ref(''),
        informacion: ref(''),
        noJugadores: ref(10),
        jugadoresXequipo: ref(10),
        tipoTorneo: ref(null), 
        tipoJuego: ref(null), 
        optionsTipo: [
        'Torneo de Liga', 'Liga y Eliminatoria', 'Eliminatoria'],
        optionsGame: [
        'Fall Guys', 'Fortnite', 'Minecraft'],
        stepper,
        onNext,
        onPrevious,
        submit,
        tournamentTBC,

        reset () {
        done1.value = false
        done2.value = false
        step.value = 1
      }
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