import './assets/main.css'
import ElementPlus from 'element-plus'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import cookiePlugin from './plugins/cookiePlugin';

import Login from './view/Login.vue';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
const loginApp = createApp(Login);
// loginApp.use(cookiePlugin);
// loginApp.use(router)
app.use(router)
app.use(ElementPlus, { size: "small", zIndex: 3000 })
app.mount('#app')
// loginApp.mount('#login')
