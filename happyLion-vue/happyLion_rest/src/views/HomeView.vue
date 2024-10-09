<template>
  <div>
    <!-- 如果尚未登入，顯示登入表單 -->
    <section v-if="!isLoggedIn" class="container px-4 py-5 mt-auto text-center text-lg-start">
      <div class="card mb-3">
        <div class="row g-0 d-flex align-items-center">
          <div class="col-lg-4 d-none d-lg-flex">
            <img src="/img/happyLion-5.jpeg" alt="Trendy Pants and Shoes"
              class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5" />
          </div>
          <div class="col-lg-8">
            <div class="card-body py-5 px-md-5">

              <form @submit.prevent="handleLogin">
                <div data-mdb-input-init class="form-outline mb-4">
                  <input type="text" v-model="username" id="form2Example1" class="form-control" required />
                  <label class="form-label" for="form2Example1">登入帳號</label>
                </div>

                <div data-mdb-input-init class="form-outline mb-4">
                  <input type="password" v-model="password" id="form2Example2" class="form-control" required />
                  <label class="form-label" for="form2Example2">密碼</label>
                </div>

                <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 如果已經登入，顯示 RouterView -->
    <RouterView v-if="isLoggedIn" :username="username" :password="password" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const isLoggedIn = ref(false); // 預設為 false，表單會顯示
const router = useRouter();
const username = ref('');
const password = ref('');

const handleLogin = () => {
  console.log('Logging in with username:', username.value, 'and password:', password.value);
  isLoggedIn.value = true; // 登入成功，更新狀態
  localStorage.setItem('isLoggedIn', 'true');
  localStorage.setItem('username', username.value); // 儲存 username 到 localStorage

  // 跳轉到 RestHomeView，並傳遞 username 和 password
  router.push({ name: 'rest_home', params: { username: username.value, password: password.value } });
};
</script>

<style>
</style>