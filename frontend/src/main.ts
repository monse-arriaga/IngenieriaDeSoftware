import Vue from 'vue';
import { createApp } from 'vue';
import './style.css';
import App from './App.vue';
import router from './router';
//import store from './store'
//import 'bootstrap'


const app = createApp(App);
app.use(router);
app.mount('#app');
