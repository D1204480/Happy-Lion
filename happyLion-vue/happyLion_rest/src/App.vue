<template>
  <div>
    <header>
      <div>
        <!-- navbar 元件裡有 RouterLink  -->
        <!-- <navbar />  -->
      </div>

    </header>

    <body class="d-flex flex-column min-vh-100"> <!-- 讓footer固定在視窗最下方-->

      <!--測試區-->

      <!-- 只有未登入時顯示登入表單 -->
      <section v-if="!isLoggedIn" class="container px-4 py-5 mt-auto text-center text-lg-start">
        <div class="card mb-3">
          <div class="row g-0 d-flex align-items-center">
            <div class="col-lg-4 d-none d-lg-flex">
              <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Trendy Pants and Shoes"
                class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5" />
            </div>
            <div class="col-lg-8">
              <div class="card-body py-5 px-md-5">

                <form>
                  <!-- Email input -->
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="acount" id="form2Example1" class="form-control" />
                    <label class="form-label" for="form2Example1">登入帳號</label>
                  </div>

                  <!-- Password input -->
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="password" id="form2Example2" class="form-control" />
                    <label class="form-label" for="form2Example2">密碼</label>
                  </div>

                  <!-- 2 column grid layout for inline styling -->
                  <div class="row mb-4">
                    <div class="col d-flex justify-content-center">
                      <!-- Checkbox -->
                      <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                        <label class="form-check-label" for="form2Example31"> Remember me </label>
                      </div>
                    </div>

                    <div class="col">
                      <!-- Simple link -->
                      <a href="#!">Forgot password?</a>
                    </div>
                  </div>

                  <!-- Submit button -->
                  <button type="button" @click="handleLogin" class="btn btn-primary btn-block mb-4">Sign in</button>
                </form>

              </div>
            </div>
          </div>
        </div>
      </section>
       
      <!--測試區 -->

      <!-- RoutrtView 切換分頁 -->
      <RouterView />

      <myFooter />
    </body>

  </div>
</template>

<!-- <script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const isLoggedIn = ref(false);
const router = useRouter();

// 檢查 localStorage 中的登入狀態
onMounted(() => {
  const savedLoginState = localStorage.getItem('isLoggedIn');
  if (savedLoginState === 'true') {
    isLoggedIn.value = true;
  }
});

const handleLogin = () => {
  // 假設登入驗證成功
  isLoggedIn.value = true;

  // 將登入狀態存儲到 localStorage
  localStorage.setItem('isLoggedIn', 'true');

  // 跳轉到 RestHomeView
  router.push({ name: 'RestHomeView' });
};
</script> -->

<style scoped>
.rounded-t-5 {
  border-top-left-radius: 0.5rem;
  border-top-right-radius: 0.5rem;
}

@media (min-width: 992px) {
  .rounded-tr-lg-0 {
    border-top-right-radius: 0;
  }

  .rounded-bl-lg-5 {
    border-bottom-left-radius: 0.5rem;
  }
}


</style>