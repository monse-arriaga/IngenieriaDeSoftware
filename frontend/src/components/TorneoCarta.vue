<template>
<q-card @click="showDetails" class="my-card bg-indigo-6" flat  bordered style="margin-top: 50px;">
  <q-card-section horizontal>
    <q-card-section class="q-pt-xs ">
      <div class="text-overliner text-center">{{ tournament.tournamentGame }}</div>
      <div class="text-h5 q-mt-sm q-mb-xs text-center">{{ tournament.name }}</div>
      <div class="text-caption text-black">
        {{ tournament.description }}
      </div>
    </q-card-section>

    </q-card-section>

  <q-separator />

  <div class="col-4">
    <q-img class="col-2"
    :src="tournament.image"
    :ratio="4/3"
  />
  </div>

  <q-card-actions>  
    <q-btn flat round icon="event" />
    <q-btn flat>
      {{ tournament.date }}
    </q-btn>
    <q-btn flat round icon="emoji_events" />
    <q-btn flat>
      {{ tournament.prize }} USD
    </q-btn>
    <q-btn flat round icon="groups" />
    <q-btn flat>
      {{ tournament.playersBT }} 
    </q-btn>
    <q-btn flat round icon="how_to_reg" />
    <q-btn flat>
      {{ tournament.inPlayers }} / {{ tournament.players }} 
    </q-btn>
    
  </q-card-actions>
</q-card>
</template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import { useRouter } from "vue-router";
//import Tournament from "../types/Tournament";

export default defineComponent({
  name: "TorneoCarta",
  props: {
    tournament: {
      type: Object as PropType<any>,
      required: true,
    },
  },
  
  setup(props) {
    const router = useRouter();
    const showDetails = () => {
      const tournament = props.tournament.name;
      router.push({ name: 'torneo', params: { tournament } });
    };
    return {
      showDetails,
    };
  },
});
</script>

<style lang="sass" scoped>
.my-card
  width: 1000%
  max-width: 370px
  
</style>