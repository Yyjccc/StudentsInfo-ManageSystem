import { createRouter, createWebHashHistory } from 'vue-router';
import Students from '../components/Students.vue'; // 假设的组件
import login from '../view/Login.vue';
import change from '../components/Change.vue';
import reward from '../components/Reward.vue';
import punishment from '../components/Punishment.vue'
import apiService from '../api'
import App from '../App.vue'
// 导入其他组件...

const routes = [
    {
        path: '/home',
        name: 'home',
        component: App
    },
    {

        path: '/login',
        name: 'login',
        component: login
    },
    {
        path: '/students',
        component: Students
    },
    // 定义其他路由...
    {
        path: '/change',
        component: change
    },
    {
        path: '/reward',
        component: reward
    },
    {
        path: '/punishment',
        component: punishment
    }

];

const router = createRouter({
    history: createWebHashHistory(),
    routes,
});



// router.beforeEach((to, from, next) => {
//     // 检查用户是否已登录
//     const isAuthenticated = true;//apiService.checkLogin();/* 根据你的登录状态检查逻辑 */;

//     if (!isAuthenticated) {
//         // 如果用户未登录且访问的不是登录页，则重定向到登录页
//         window.location.href = "/login.html";
//     }
// });

export default router;
