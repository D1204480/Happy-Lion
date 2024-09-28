<template>
  <div>
    <header>
      <div>
        <!-- navbar 元件裡有 RouterLink  -->
        <!-- <navbar />  -->
      </div>

    </header>

    <body class="d-flex flex-column min-vh-100">  <!-- 讓footer固定在視窗最下方-->
      
<!--測試區-->

<!--測試區 -->

      <!-- RoutrtView 切換分頁 -->
      <RouterView />

      <myFooter />
    </body>

  </div>
</template>

<script setup>
import RestView from './views/RestView.vue';
</script>

<style scoped>
/* @media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }
} */
</style>