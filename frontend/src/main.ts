import { createApp } from 'vue'
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import './style.css'
import App from './App.vue'
import BusquedaTorneos from './components/BusquedaTorneos.vue';
import PaginaInicio from './components/PaginaInicio.vue';

//createApp(App).mount('#app')

const routes: Array<RouteRecordRaw> = [
    {
      path: '/busqueda-torneos',
      name: 'BusquedaTorneos',
      component: BusquedaTorneos
    },
    {
      path: '/',
      name: 'pagina-de-inicio',
      component: PaginaInicio
    },
    // Agrega más rutas aquí si es necesario
  ];
  
  const router = createRouter({
    history: createWebHistory(),
    routes
  });
  
  export default router;


const app = createApp(App);
app.use(router);
app.mount('#app');
