<template>
  <div>
    <!-- <div class="my-3"> -->
      <!-- <h4>餐廳編號: {{ searchQuery }}</h4> -->
      <!-- 這裡可以顯示或處理搜尋結果 -->
    <!-- </div> -->

    <!-- navbar navigation -->
    <div class="card text-start">
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
          <li class="nav-item">
            <router-link class="nav-link" :class="{ active: activeTab === 'restaurant' }" to="/rest_home"
              @click.prevent="setActiveTab('restaurant')">
              餐廳基本資料
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :class="{ active: activeTab === 'menu' }" to="/rest_menu"
              @click.prevent="setActiveTab('menu')">
              菜單
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :class="{ active: activeTab === 'orders' }" to="/rest_order"
              @click.prevent="setActiveTab('orders')">
              訂單
            </router-link>
          </li>
        </ul>
      </div>

      <router-view></router-view> <!-- 動態加載頁面 -->

      <!-- navigation 顯示區 -->
      <!-- <div class="card-body">
        <h5 class="card-title">{{ currentTitle }}</h5>
        <p class="card-text">{{ currentText }}</p>
      </div> -->

    </div>



  </div>
</template>


<script>
export default {
  props: {
    searchQuery: String
  },
  
  data() {
    return {
      activeTab: 'restaurant', // 預設為餐廳基本資料
      tabsContent: {
        restaurant: {
          title: '餐廳名字',
          text: '這裡是餐廳的基本資料內容。'
        },
        menu: {
          title: '菜單',
          text: '這裡是餐廳的菜單內容。'
        },
        orders: {
          title: '訂單',
          text: '這裡是餐廳的訂單內容。'
        }
      },
    };
  },

  watch: {
    // 當路由變化時，根據路由的 meta 設定 activeTab
    $route(to) {
      this.activeTab = to.meta.activeTab || 'restaurant';
    }
  },

  mounted() {
    // 頁面載入時根據當前路由設定 activeTab
    this.activeTab = this.$route.meta.activeTab || 'restaurant';
  },

};
</script>

<style>
.nav-link {
  cursor: pointer;
}
</style>