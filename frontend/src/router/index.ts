import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import BusquedaTorneos from "../components/BusquedaTorneos.vue"; 
import PaginaInicio from "../components/PaginaInicio.vue"; 
import Registro from '../components/Registro.vue';
import InicioSesion from '../components/InicioSesion.vue';
import Configuracion from '../components/Configuracion.vue';
//import Torneo from '../components/Torneo.vue';
import CrearTorneo from '../components/CrearTorneo.vue';

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
    {
      path: '/registro',
      name: 'registro',
      component: Registro
    },
    {
      path: '/iniciar-sesion',
      name: 'inicio-sesion',
      component: InicioSesion
    },
    {
      path: '/configuracion',
      name: 'configuracion',
      component: Configuracion
    },
    /*
    {
      path: '/torneo',
      name: 'torneo',
      component: Torneo
    },
    */
    {
      path: '/crear-torneo',
      name: 'crear-torneo',
      component: CrearTorneo
    },
    { path: '/:pathMatch(.*)*', component: PaginaInicio },
  ];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
