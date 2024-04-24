<template>
<q-card class="my-card" flat bordered>
  <q-card-section horizontal>
    <q-card-section class="q-pt-xs">
      <div class="text-overline">Valorant</div>
      <div class="text-h5 q-mt-sm q-mb-xs">{{ tournament.name }}</div>
      <div class="text-caption text-grey">
        {{ tournament.description }}
      </div>
    </q-card-section>

    <q-img
        class="col-5"
        src="https://cdn.quasar.dev/img/parallax2.jpg"
      />
    </q-card-section>

  <q-separator />

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
import Tournament from "../types/Tournament";

export default defineComponent({
  name: "TorneoCarta",
  props: {
    tournament: {
      type: Object as PropType<Tournament>,
      required: true,
    },
  },
  setup(props) {
    const router = useRouter();
    const showDetails = () => {
      router.push({ name: "details", params: { id: props.tournament.name } });
    };
    return {
      showDetails,
    };
  },
});
</script>

<style lang="sass" >
.my-card 
    color: black
</style>