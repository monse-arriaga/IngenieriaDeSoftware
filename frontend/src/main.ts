import { createApp } from 'vue';
import './style.css'; // Assuming this is for global styles
import App from './App.vue';
import router from './router';
import { Quasar } from 'quasar';
import '@quasar/extras/material-icons/material-icons.css'
import 'quasar/src/css/index.sass'
import '@quasar/extras/mdi-v7/mdi-v7.css'

const app = createApp(App);
app.use(Quasar, {
    plugins: {},
});
app.use(router);
app.mount('#app');
