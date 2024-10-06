import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/HomeView.vue'; // 引入你的 Home 組件
import Rest_HomeView from '../views/Rest_HomeView.vue'
import Rest_MenuView from '../views/Rest_MenuView.vue'
import Rest_OrderView from '../views/Rest_OrderView.vue'
import Rest_OrderContentView from '@/views/Rest_OrderContentView.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home', // 可選的，給這個路由一個名稱
      component: Home, // 對應的組件
    },

    {
      path: '/rest_home',  // 將 params 定義在路徑中
      name: 'rest_home',
      component: Rest_HomeView,
      meta: { activeTab: 'restaurant' },  // 設定為 'restaurant'
      // props: route => ({ username: route.query.username, password: route.query.password }) // 將查詢參數作為 props 傳遞
    },
    {
      path: '/rest_menu', 
      name: 'rest_menu',
      component: Rest_MenuView,
      meta: { activeTab: 'menu' }  // 設定為 'menu'
    },
    {
      path: '/rest_order',
      name: 'rest_order',
      component: Rest_OrderView,
      meta: { activeTab: 'orders' }  // 設定為 'orders'
    },
    {
      path: '/rest_order_content',
      name: 'rest_order_content',
      component: Rest_OrderContentView,
      // meta: { activeTab: 'orders' }  // 設定為 'orders'
    },

  ]
})

export default router
