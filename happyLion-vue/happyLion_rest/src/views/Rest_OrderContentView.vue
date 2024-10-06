<script setup>
import Rest_order_content from '../components/Rest_OrderContent.vue'
</script>

<template>
    <div>
    <!-- Navbar 組件負責處理使用者輸入的搜尋 -->
    <!-- <Navbar @search="updateSearchQuery" /> -->

    <!-- Rest_order_content 組件顯示或使用搜尋結果 -->
    <Rest_order_content :searchQuery="searchQuery" />

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
    Rest_order_content,
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
