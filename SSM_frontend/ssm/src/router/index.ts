import { createRouter, createWebHistory } from 'vue-router'
 
const routerHistory = createWebHistory()
 
const router = createRouter({
  history: routerHistory,
  routes: [
    {
      path: '/',
      name: "Login",
      component: () => import('../components/Login.vue'),
    },
    {
      path: '/StudentClient',
      name: "StudentClient",
      component: () => import('../components/StudentClient.vue')
    },
    {
      path: '/AdminClient',
      name: "AdminClient",
      component: () => import('../components/AdminClient.vue')
    }
  ]
})

export default router
