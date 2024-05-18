<template>
  <div class="flex">
    <!-- Backdrop -->
    <div
    :class="isOpen ? 'block' : 'hidden'"
    class="fixed inset-0 z-20 transition-opacity bg-black opacity-50 lg:hidden"
    @click="isOpen = false"
    />
    <!-- End Backdrop -->

    <div
    :class="isOpen ? '-translate-x-0 ease-out' : 'translate-x-full ease-in'"
    class="fixed inset-y-50 right-0 z-30 w-64 overflow-y-auto transition duration-300 transform bg-gray-900 lg:translate-x-8 lg:static lg:inset-12"
    >
      <div class="flex items-center justify-center mt-8">
        <div class="flex items-center">
          <svg
            class="w-12 h-12"
            viewBox="0 0 550 550"
            fill="#4C51BF"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
            d="M491.103,215.312c-8.841-24.651-22.222-47.73-39.651-67.984l41.965-73.324L368.537,2.532l-41.965,73.324    c-26.307-4.777-52.972-4.618-78.703,0.245L205.384,3.135L81.035,75.571l42.485,72.966c-8.394,9.89-16.105,20.572-22.807,32.28    c-6.683,11.677-11.989,23.736-16.282,36.013L0,217.154l0.529,143.909l84.45-0.318c8.822,24.68,22.194,47.711,39.636,67.979    l-41.965,73.32l124.878,71.473l41.965-73.324c26.292,4.768,52.954,4.648,78.672-0.264l42.501,72.979l124.349-72.434l-42.487-72.93    c8.394-9.932,16.138-20.633,22.821-32.311c6.701-11.707,11.989-23.779,16.298-36.043l84.4-0.303l-0.529-143.909L491.103,215.312z     M413.733,359.967c-39.734,69.426-128.229,93.496-197.655,53.762s-93.495-128.23-53.761-197.655    c39.734-69.425,128.229-93.495,197.654-53.761C429.397,202.047,453.468,290.542,413.733,359.967z"
            fill="#4C51BF"
            stroke="#4C51BF"
            stroke-width="0"
            stroke-linecap="round"
            stroke-linejoin="round"
          />

          </svg>

          <span class="mx-2 text-2xl font-semibold text-white">Settings</span>
        </div>
      </div>

      <nav class="mt-10">
        <router-link
          class="flex items-center px-6 py-2 mt-4 duration-200 border-l-4"
          :class="[$route.name === 'Dashboard' ? activeClass : inactiveClass]"
          to="/Configuracion"
        >
          <svg
            class="w-5 h-5"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M2 10C2 5.58172 5.58172 2 10 2V10H18C18 14.4183 14.4183 18 10 18C5.58172 18 2 14.4183 2 10Z"
              fill="currentColor"
            />
            <path
              d="M12 2.25195C14.8113 2.97552 17.0245 5.18877 17.748 8.00004H12V2.25195Z"
              fill="currentColor"
            />
          </svg>

          <span class="mx-4">Mi perfil</span>
        </router-link>

        <router-link
          class="flex items-center px-6 py-2 mt-4 duration-200 border-l-4"
          :class="[$route.name === 'Blank' ? activeClass : inactiveClass]"
          to="/mis-torneos"
        >
          <svg class="w-5 h-5" @click="() => goToPage('/mis-torneos')" fill="currentColor" viewBox="0 0 20 20">
            <path
              d="M3 4a1 1 0 011-1h12a1 1 0 011 1v2a1 1 0 01-1 1H4a1 1 0 01-1-1V4zM3 10a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H4a1 1 0 01-1-1v-6zM14 9a1 1 0 00-1 1v6a1 1 0 001 1h2a1 1 0 001-1v-6a1 1 0 00-1-1h-2z"
            />
          </svg>

          <span class="mx-4">Mis participaciones</span>
        </router-link>
      </nav>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useSidebar } from '../composables/useSideBar'
import { useRouter } from 'vue-router';

const router = useRouter()
const { isOpen } = useSidebar()
const activeClass = ref(
  'bg-gray-600 bg-opacity-25 text-gray-100 border-gray-100',
)
const inactiveClass = ref(
  'border-gray-900 text-gray-500 hover:bg-gray-600 hover:bg-opacity-25 hover:text-gray-100',
)
const goToPage = (route: string) => {
    console.log(1)
    router.push({ path: route });
}
</script>1