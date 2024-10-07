<template>
  <div>
    <nav class="navbar navbar-expand-md bg-body-tertiary">
      <div class="container-fluid">
        <!-- Sidebar Button -->
        <button class="btn btn-light me-2" type="button" data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">@@</button>

        <!-- Navbar Brand -->
        <a class="navbar-brand" href="#">Happy Lion</a>

        <!-- Navbar Toggler for mobile view -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
          aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar Links -->
        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
          <ul class="navbar-nav me-auto mb-lg-0">
            <li class="nav-item">
              <RouterLink to="/rest_home" class="nav-link active" aria-current="page">Home</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/rest_menu" class="nav-link">Menu</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/rest_order" class="nav-link">Order</RouterLink>
            </li>
          </ul>
          <form class="d-flex" role="search" @submit.prevent="handleSearch">
            <input class="form-control me-2" type="search" placeholder="Search" v-model="searchQuery"
              aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>

        <!-- user_icon Avatar dropdown  -->
        <div class="dropdown mx-1" v-if="isLoggedIn"> <!-- 這裡的 isLoggedIn 基於 localStorage -->
          <a class="dropdown-toggle d-flex align-items-center hidden-arrow" href="#" id="navbarDropdownMenuAvatar"
            role="button" data-bs-toggle="dropdown" aria-expanded="false" data-bs-display="static">
            <img src="https://picsum.photos/200" class="rounded-circle" height="35" alt="user_icon"
              loading="lazy" />
          </a>
          <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuAvatar">
            <li>
              <a class="dropdown-item" href="#">My profile</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">Settings</a>
            </li>
            <li>
              <!-- 將 @click 事件綁定到 Logout 按鈕上 -->
              <a class="dropdown-item" href="#" @click="logout">Logout</a>
            </li>
          </ul>
        </div>

      </div>
    </nav>

    <!-- Sidebar Offcanvas -->
    <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions"
      aria-labelledby="offcanvasWithBothOptionsLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">Happy Lion</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <p>Customer</p>
        <p>Restaurant</p>
        <p>Delivery</p>
        <p>System Management</p>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: '',  // 綁定搜尋欄的輸入值
      isLoggedIn: false,  // 預設未false
    };
  },

  created() {
    if (localStorage.getItem('isLoggedIn') === 'true') {
      this.isLoggedIn = true; // 當已登入，顯示 avatar
    }
  },

  watch: {
    $route(to, from) {
      // 每次路由發生變化時檢查 localStorage
      this.isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    }
  },

  methods: {
    handleSearch() {
      if (this.searchQuery) {
        this.$emit('search', this.searchQuery); // 發送搜尋字串到父組件
      }
    },

    logout() {
      // 清空 localStorage 的 username
      localStorage.removeItem('username');
      localStorage.removeItem('isLoggedIn');
      this.isLoggedIn = false; // 隱藏 avatar

      // 根據需要，跳轉到登錄頁面或首頁
      this.$router.push({ name: 'Home' });
    }
  }
};
</script>

<style>
/* 確保按鈕和標籤在同一行 */
.navbar-brand {
  display: flex;
  align-items: center;
}

.btn-light {
  margin-right: 10px;
  /* 調整按鈕與標籤的間距 */
}

.dropdown-menu {
  position: absolute;
  right: 0;
  /* 將選單對齊到右側，避免超出視窗 */
}
</style>