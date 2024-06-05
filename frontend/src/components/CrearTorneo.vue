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
             
          <q-input dark filled label-color="white" v-model="tournamentTBC.name" :rules="[val => val.length <= 100 || 'Máximo 100 caracteres']" label="Nombre del torneo" :input-style="{ color: 'white' }" />
          
          <div style="margin-top: 20px;"></div>

          <div class="input-container">
            <!-- Input para la fecha del torneo -->
            <q-input dark filled label-color="white" v-model="tournamentTBC.date" label="Fecha del torneo"  :rules="[val => !isPastDate(val) || 'Selecciona una fecha futura']"  type="date" :input-style="{ color: 'white'}" style="max-width: 3000px" />
            
            <!-- Espacio entre los inputs -->
            <div style="width: 20px;"></div>
            
            <!-- Input para la hora del torneo -->
            <q-input dark filled label-color="white" v-model="tournamentTBC.time" label="Hora del torneo"  :rules="[val => !isPastTime(val) || 'Selecciona una hora futura']" type="time" :input-style="{ color: 'white'}" style="max-width: 3000px" />
          </div>

        <!-- Selector de opciones múltiples -->
        <div style="margin-top: 40px;"></div>
        <div class="q-gutter-md">
        <q-select dark filled v-model="tournamentTBC.tournamentGame" :options="optionsGame" label="Selecciona el juego" />
        </div>


        <div style="margin-top: 40px;"></div>
        <q-input dark
        v-model="tournamentTBC.description"
        filled
        clearable
        autogrow
        :rules="[val => val.length <= 250 || 'Máximo 250 caracteres']"
        label="Da una breve descripción de tu torneo para los futuros participantes"
        />

        <q-stepper-navigation>
          <q-btn @click="() => { done1 = true; step = 2 }" color="primary" label="Continue" />
        </q-stepper-navigation>
     
        </q-step>

        <q-step
          :name="2"
          title="Configuremos algunas cosas para tu torneo"
          icon="settings"
          :done="step>2"
        >

        <!-- Selector de opciones múltiples -->
        <div style="margin-top: 40px;"></div>
        <div class="q-gutter-md">
        <q-select dark filled v-model="tournamentTBC.tournamentType" :options="optionsTipo" label="Selecciona el tipo de torneo" />
        </div>

        <div style="margin-top: 40px;"></div>
        <div class="input-container">
        <q-select dark filled v-model="ronda" :options="optionsRonda" label="Tipo de Enfrentamienfo"  style="max-width: 350px"/>
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
        <div class="input-container">
        <q-checkbox dark v-model="bracket.balanceByes" label="Byes vs Byes" />

        <q-checkbox dark v-model="bracket.consolationFinal" label="Final Tercer Lugar" />

        <q-checkbox dark v-model="bracket.skipFirstRound" label="Entrar como Perdedores" />
        </div>

        <q-stepper-navigation>
          <q-btn @click="() => { done1 = true; step = 3 }" color="primary" label="Continue" />
        </q-stepper-navigation>
        
        </q-step>
  
        <q-step
          :name="3"
          title="Selecciona la imagen que tendrá tu torneo"
          icon="group_add"
          :done="step > 3"
        >
          Ahora es momento de que agreguemos la imagen que representará tu torneo. <p></p>
          1. Escogela en un navegador web.<p></p>
          1. Da click izquierddo y selecciona "Copy image address".<p></p>
          2. Pega lo que copiaste aquí. <p></p>
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
            <q-btn color="primary" @click="submit" v-if="step === 3" label="Finish" />
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
  import { useRouter } from 'vue-router' 
  import { GrandFinalType, RoundRobinMode, StageSettings, StageType } from 'brackets-model';
  import tournamentStorage from '../store/tournament';
  import { BracketsManager } from 'brackets-manager';
  export default defineComponent({
    setup () {
      const optionsRonda = new Map<string, RoundRobinMode> ([
        ["Doble", "simple"],
        ["Directa", "double"]
    ]);
      const optionsTipo = new Map<string, StageType> ([
        ["Liga", 'round_robin'],
        ["Eliminación Directa", 'single_elimination'],
        ["Eliminación Doble", 'double_elimination']
      ])
      const optionFinal = new Map<string, GrandFinalType> ([
        ["Simple", "simple"],
        ["Reinicio", "double"]
      ])
      const stepper = ref() as Ref<QStepper>
      const step = ref(1)
      const done1 = ref(false)
      const final = ref("Simple");
      const ronda = ref("Doble")
      const done2 = ref(false)
      const router = useRouter();
      const storage = new tournamentStorage();
      const manager = new BracketsManager(storage);  
      const onNext = () => {
        if (stepper.value) {
          stepper.value.next();
        }
      };

      const isPastDate = (dateString: string) => {
      const selectedDate = new Date(dateString);
      const currentDate = new Date();
      return selectedDate < currentDate;
    };

    const isPastTime = (timeString: string) => {
      const selectedTime = new Date(`2000-01-01T${timeString}`);
      const currentTime = new Date();
      return selectedTime < currentTime;
    };

      const onPrevious = () => {
        if (stepper.value) {
          stepper.value.previous();
        }
      };
      const tournamentTBC = ref<Tournament>({
        name: "MyTournament",
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

      const bracket = ref<StageSettings>({
        balanceByes: false,
        consolationFinal: false,
        skipFirstRound: false,
        roundRobinMode: optionsRonda.get(ronda.value),
        grandFinal: optionFinal.get(final.value)
      })

      const submit =  () => {
        const tPlayers:(string | null)[] = []
        if ((tournamentTBC.value.players & (tournamentTBC.value.players - 1)) === 0) {
          for (let index = 0; index < tournamentTBC.value.players; index++) {
            tPlayers.push("Player " + tournamentTBC.value.name + " " + index)
          }
        } else {
          let players = tournamentTBC.value.players;
          let count = 0;
          while (players > 0) {
              players >>= 1;
              count++;
          }
          players = 1 << count
          for (let index = 0; index < tournamentTBC.value.players; index++) {
            tPlayers.push("Player " + tournamentTBC.value.name + " " + index)
          }
          for (let index = tournamentTBC.value.players; index < players; index++) {
            tPlayers.push(null)
          }
        }
        TournamentService.tournament(tournamentTBC.value).then(async () => {
          await manager.create.stage({
            tournamentId: tournamentTBC.value.name,
            name: "Fase de Eliminación",
            type: optionsTipo.get(tournamentTBC.value.tournamentType) as StageType,
            seeding: tPlayers,
            settings: bracket.value
          })
          router.push({name: "pagina-de-inicio"});
        });
        
        window.location.reload;
      };
      return {
        step: ref(1),
        done1,
        done2,
        optionsTipo: ["Eliminación Directa", "Eliminación Doble"],
        optionsFinal:["Simple", "Reinicio"],
        optionsGame: [
        'Fall Guys', 'Fortnite', 'Minecraft'],
        optionsRonda: ["Directa", "Doble"],
        stepper,
        onNext,
        onPrevious,
        submit,
        tournamentTBC,
        bracket,
        ronda,
        isPastDate,
        isPastTime,

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