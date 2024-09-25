<template>
  <div class="card text-start">
    <div class="card-header">
      <ul class="nav nav-tabs card-header-tabs">
        <li class="nav-item">
          <a class="nav-link" :class="{ active: activeTab === 'restaurant' }" href="#"
            @click.prevent="setActiveTab('restaurant')">餐廳基本資料</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" :class="{ active: activeTab === 'menu' }" href="#"
            @click.prevent="setActiveTab('menu')">菜單</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" :class="{ active: activeTab === 'orders' }" href="#"
            @click.prevent="setActiveTab('orders')">訂單</a>
        </li>
      </ul>
    </div>
    <div class="card-body">
      <h5 class="card-title">{{ currentTitle }}</h5>
      <p class="card-text">{{ currentText }}</p>

      <div class="card" style="margin-top: 1.5em;">
        <div class="card-body">
          <table class="table table-hover">
            <tbody> 
              <!-- 餐廳編號 -->
              <tr>
                <th scope="row">餐廳編號: </th>
                <td>{{ restaurant.restaurantId }}</td>
              </tr>

              <!-- 餐廳名稱 -->
              <tr>
                <th scope="row">餐廳名稱: </th>
                <td>{{ restaurant.firstName }}</td>
              </tr>

              <!-- 電話 -->
              <tr>
                <th scope="row">電話: </th>
                <td>{{ restaurant.lastName }}</td>
              </tr>

              <!-- 郵遞區號 -->
              <tr>
                <th scope="row">郵遞區號: </th>
                <td>{{ restaurant.birthday }}</td>
              </tr>

              <!-- 地址 -->
              <tr>
                <th scope="row">地址: </th>
                <td>{{ restaurant.address }}</td>
              </tr>
            </tbody>

             <!-- <thead>
              <tr>
                <th scope="col">餐廳編號</th>
                <th scope="col">餐廳名稱</th>
                <th scope="col">電話</th>
                <th scope="col">郵遞區號</th>
                <th scope="col">地址</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="restaurant in restaurants" v-bind:key="restaurant.restaurantId">
                <td>{{ restaurant.restaurantId }}</td>
                <td>{{ restaurant.firstName }}</td>
                <td>{{ restaurant.lastName }}</td>
                <td>{{ restaurant.birthday }}</td>
                <td>{{ restaurant.email }}</td>
                <td>{{ restaurant.address }}</td>
              </tr>
            </tbody>  -->
          </table>
        </div>
      </div>
      <div class="text-center">
        <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
        <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editrestaurantModal"
          style="margin-right: 0.5em;" v-on:click="onUpdaterestaurant(restaurant)">Edit</button>
        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleterestaurantModal"
          v-on:click="onSelectrestaurant(restaurant)">Delete</button>
      </div>
    </div>
  </div>
</template>


<script>
export default {

  data() {
    return {
      activeTab: 'restaurant', // 預設為餐廳基本資料
      tabsContent: {
        restaurant: {
          title: '餐廳名字',
          text: '這裡是餐廳的基本資料內容。'
        },
        menu: {
          title: '菜單',
          text: '這裡是餐廳的菜單內容。'
        },
        orders: {
          title: '訂單',
          text: '這裡是餐廳的訂單內容。'
        }
      }
    };
  },
  computed: {
    currentTitle() {
      return this.tabsContent[this.activeTab].title;
    },
    currentText() {
      return this.tabsContent[this.activeTab].text;
    }
  },
  methods: {
    setActiveTab(tab) {
      this.activeTab = tab; // 切換當前的 tab
    }
  }
};
</script>

<style>
.nav-link {
  cursor: pointer;
}
</style>
