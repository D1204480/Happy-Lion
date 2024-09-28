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
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
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
      searchQuery: ''  // 綁定搜尋欄的輸入值
    };
  },
  methods: {
    handleSearch() {
      if (this.searchQuery) {
         this.$emit('search', this.searchQuery); // 發送搜尋字串到父組件
      }
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
</style>