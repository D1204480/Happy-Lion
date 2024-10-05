<script setup>
import Rest_home from '../components/Rest_home.vue'
import Navbar from '../components/Navbar.vue';
import Rest_nav_v2 from '../components/Rest_nav_v2.vue';
</script>

<template>
  <div>
    <div>
      <!-- Navbar 組件負責處理使用者輸入的搜尋 -->
      <Navbar @search="updateSearchQuery" />

      <!-- Rest_nav_v2, Rest_home, 顯示或使用 searchQuery 結果 -->
      <!-- 傳遞 username 到 rest_home.vue -->
      <Rest_nav_v2 :searchQuery="searchQuery" />
      <Rest_home :searchQuery="searchQuery" :username="username" />

    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      searchQuery: '' // 這裡存儲搜尋的內容
    };
  },

  computed: {
    // 從路由中獲取 username 和 password
    username() {
      return this.$route.params.username;
    },
    password() {
      return this.$route.params.password;
    }
  },

  methods: {
    // 處理從 Navbar 接收到的搜尋查詢
    updateSearchQuery(query) {
      this.searchQuery = query; // 更新搜尋查詢
      console.log("Updated search query:", this.searchQuery);
    },
  },

  components: {
    Navbar,
    Rest_nav_v2,
    Rest_home,
  }
};
</script>