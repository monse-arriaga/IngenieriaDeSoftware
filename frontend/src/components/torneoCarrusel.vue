<template>
    <q-card @click="showDetails" class="my-card bg-indigo-6" flat  bordered style="margin-top: 5px;">
      <q-card-section horizontal>
        <div class="col-4">
            <q-img class="col-2"
            :src="tournament.image"
            style="width: 720px; height: 470px; object-fit: cover">
          <div class="absolute-bottom text-subtitle1 text-center">
            <h3>{{ tournament.name }}</h3>
          </div>
        </q-img>
          </div>
    
        </q-card-section>
    
      <q-separator />
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

