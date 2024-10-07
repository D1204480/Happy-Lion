// import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import navbar from './components/Navbar.vue'
import myFooter from './components/Footer.vue'


const app = createApp(App)

app.use(router)
app.component('navbar', navbar);
app.component('myFooter', myFooter);

app.mount('#app')



  