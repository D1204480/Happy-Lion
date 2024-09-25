<template>
  <div>
    <header>
      <div>
        <!-- navbar 元件裡有 RouterLink -->
        <navbar />
      </div>
    </header>

    <!-- RoutrtView 切換分頁 -->
    <RouterView />

    <myFooter />
  </div>
</template>

<script setup>
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
