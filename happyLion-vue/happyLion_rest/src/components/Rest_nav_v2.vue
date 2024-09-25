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
          <!-- 餐廳基本資料表格 -->
          <table class="table table-hover" v-if="activeTab === 'restaurant'">
            <!-- <tbody>
                <tr>
                  <th scope="row">餐廳編號:</th>
                  <td>{{ selectedRestaurant.restaurantId }}</td>
                </tr>
                <tr>
                  <th scope="row">餐廳名稱:</th>
                  <td>{{ selectedRestaurant.firstName }}</td>
                </tr>
                <tr>
                  <th scope="row">電話:</th>
                  <td>{{ selectedRestaurant.lastName }}</td>
                </tr>
                <tr>
                  <th scope="row">郵遞區號:</th>
                  <td>{{ selectedRestaurant.birthday }}</td>
                </tr>
                <tr>
                  <th scope="row">地址:</th>
                  <td>{{ selectedRestaurant.address }}</td>
                </tr>
              </tbody> -->

            <thead>
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
            </tbody>
          </table>

          <!-- 菜單表格 -->
          <table class="table table-hover" v-if="activeTab === 'menu'">
            <thead>
              <tr>
                <th scope="col">菜品名稱</th>
                <th scope="col">價格</th>
                <th scope="col">描述</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in menuItems" v-bind:key="item.id">
                <td>{{ item.name }}</td>
                <td>{{ item.price }}</td>
                <td>{{ item.description }}</td>
              </tr>
            </tbody>
          </table>

          <!-- 訂單表格 -->
          <table class="table table-hover" v-if="activeTab === 'orders'">
            <thead>
              <tr>
                <th scope="col">訂單編號</th>
                <th scope="col">顧客名稱</th>
                <th scope="col">訂單日期</th>
                <th scope="col">狀態</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in orders" v-bind:key="order.id">
                <td>{{ order.id }}</td>
                <td>{{ order.customerName }}</td>
                <td>{{ order.date }}</td>
                <td>{{ order.status }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="text-center">
        <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editrestaurantModal"
          style="margin-right: 0.5em;" v-on:click="onUpdaterestaurant(selectedRestaurant)">Edit</button>
        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleterestaurantModal"
          v-on:click="onSelectrestaurant(selectedRestaurant)">Delete</button>
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
      },
      // 假設這裡是選定的餐廳資料
      selectedRestaurant: {
        restaurantId: '1',
        firstName: '餐廳A',
        lastName: '12345678',
        birthday: '100000',
        address: '某地區'
      },
      // 假設這裡是菜單和訂單的數據
      menuItems: [],
      orders: []
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