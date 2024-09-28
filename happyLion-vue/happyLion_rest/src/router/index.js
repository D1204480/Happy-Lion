import { createRouter, createWebHistory } from 'vue-router'
import Rest_HomeView from '../views/Rest_HomeView.vue'
import Rest_MenuView from '../views/Rest_MenuView.vue'
import Rest_OrderView from '../views/Rest_OrderView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/rest_home',
      name: 'rest_home',
      component: Rest_HomeView,
      meta: { activeTab: 'restaurant' }  // 設定為 'restaurant'
    },
    {
      path: '/rest_menu',
      name: 'rest_menu',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: Rest_MenuView,
      meta: { activeTab: 'menu' }  // 設定為 'menu'
    },
    {
      path: '/rest_order',
      name: 'rest_order',
      component: Rest_OrderView,
      meta: { activeTab: 'orders' }  // 設定為 'orders'
    }
  ]
})

export default router
