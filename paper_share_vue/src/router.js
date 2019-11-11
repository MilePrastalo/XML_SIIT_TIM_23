import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'Index',
            component: () => import('./components/index.vue')
        },
        {
            path: '/login',
            name: 'LogIn',
            component: () => import('./components/login.vue')
        },
        {
            path: '/register',
            name: 'Register',
            component: () => import('./components/register.vue')
        },

    ]
})