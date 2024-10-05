<script setup>
import Rest_menu from '../components/Rest_menu.vue'
import Navbar from '../components/Navbar.vue';
import Rest_nav_v2 from '../components/Rest_nav_v2.vue';
</script>

<template>
  <div>
    <div>
      <!-- Navbar 組件負責處理使用者輸入的搜尋 -->
      <!-- <Navbar @search="updateSearchQuery" /> -->

      <!-- Rest_nav_v2 組件顯示或使用搜尋結果 -->
      <Rest_nav_v2 :searchQuery="searchQuery" />
      <Rest_menu :searchQuery="searchQuery" />

    </div>
  </div>
</template>

<script>
export default {
  props: {
    username: {
      type: String,
      required: false,
      default: ''
    },
    password: {
      type: String,
      required: false,
      default: ''
    }
  },

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
    }
  },

  components: {
    Navbar,
    Rest_nav_v2,
    Rest_menu,
  },

  mounted() {
    // 如果 props.username 為空，從 localStorage 讀取
    if (!this.username) {
      this.localUsername = localStorage.getItem('username') || '';
    } else {
      this.localUsername = this.username; // 如果 props 傳入了 username，將其存到本地變數
    }

    console.log("Received username:", this.localUsername);
  }
};
</script>

<style>
</style>
