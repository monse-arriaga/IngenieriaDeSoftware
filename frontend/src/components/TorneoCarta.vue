<template>
    <div class="card">
      <div class="card-header">
        <div class="card-header-left">
          <h2 class="card-title">{{ tournament.name }}</h2>
        </div>
        <div class="card-header-right">
          <button class="btn" @click="editEmployee">Edit</button>
          <button class="btn" @click="deleteEmployee">Delete</button>
          <button class="btn" @click="showDetails">Details</button>
        </div>
      </div>
      <div class="card-body">
        <div class="card-row">
          <div class="card-label">Phone:</div>
          <div class="card-value">{{ employee.bio }}</div>
        </div>
        <div class="card-row">
          <div class="card-label">Email:</div>
          <div class="card-value">{{ employee.email }}</div>
        </div>
      </div>
    </div>
  </template>

<script lang="ts">
import { defineComponent, PropType } from "vue";
import { useRouter } from "vue-router";
import User from "../types/Tournament";

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
      router.push({ name: "details", params: { id: props.employee.name } });
    };

    const editEmployee = () => {
      router.push({
        name: "edit",
        params: { id: props.employee.name },
      });
    };
    const deleteEmployee = () => {
    };

    return {
      showDetails,
      editEmployee,
      deleteEmployee,
    };
  },
});
</script>