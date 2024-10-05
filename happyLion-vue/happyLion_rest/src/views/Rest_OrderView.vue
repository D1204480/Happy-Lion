<script setup>
import Rest_order from '../components/Rest_order.vue'
import Navbar from '../components/Navbar.vue';
import Rest_nav_v2 from '../components/Rest_nav_v2.vue';
</script>

<template>
    <div>
    <!-- <Rest_nav /> -->

    <div>
      <!-- Navbar 組件負責處理使用者輸入的搜尋 -->
      <!-- <Navbar @search="updateSearchQuery" /> -->

      <!-- Rest_nav_v2 組件顯示或使用搜尋結果 -->
      <Rest_nav_v2 :searchQuery="searchQuery" />

      <Rest_order :searchQuery="searchQuery" />

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
    Rest_order,
  }
};
</script>