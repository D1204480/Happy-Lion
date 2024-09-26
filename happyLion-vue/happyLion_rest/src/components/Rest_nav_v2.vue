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
              <tr v-for="restaurant in restaurants" v-bind:key="restaurant.restId">
                <td>{{ restaurant.restId }}</td>
                <td>{{ restaurant.name }}</td>
                <td>{{ restaurant.tel }}</td>
                <td>{{ restaurant.zipcode }}</td>
                <td>{{ restaurant.address }}</td>
              </tr>
            </tbody>
          </table>

          <!-- 菜單表格 -->
          <table class="table table-hover" v-if="activeTab === 'menu'">
            <thead>
              <tr>
                <th scope="col">菜單編號</th>
                <th scope="col">品項</th>
                <th scope="col">價格</th>
                <th scope="col">描述</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in menuItems" v-bind:key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.item }}</td>
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
                <th scope="col">訂單日期</th>
                <th scope="col">顧客名稱</th>
                <th scope="col">餐廳名稱</th>
                <th scope="col">餐點</th>
                <th scope="col">單價</th>
                <th scope="col">數量</th>
                <th scope="col">總金額</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in orders" v-bind:key="order.orderId">
                <td>{{ order.orderId }}</td>
                <td>{{ order.orderDate }}</td>
                <td>{{ order.customerName }}</td>
                <td>{{ order.restaurantName }}</td>
                <td>{{ order.menuItem }}</td>
                <td>{{ order.menuPrice }}</td>
                <td>{{ order.quantity }}</td>
                <td>{{ order.totalPrice }}</td>
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

      <!-- Delete Student Modal -->
      <!-- <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              Are you sure you want to delete this item?
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                v-on:click="deleteStudent">Delete</button>
            </div>
          </div>
        </div>
      </div> -->

      <!-- Edit Student Modal -->
      <!-- <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel"
        aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Edit</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="mb-3 row">
                <label for="editFirstName" class="col-sm-3 col-form-label">First Name</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="editFirstName" v-model="editStudent.firstName"
                    aria-label="First name">
                </div>
              </div>
              <div class="mb-3 row">
                <label for="editLastName" class="col-sm-3 col-form-label">Last Name</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="editLastName" v-model="editStudent.lastName"
                    aria-label="First name">
                </div>
              </div>
              <div class="mb-3 row">
                <label for="editBirthday" class="col-sm-3 col-form-label">Birthday</label>
                <div class="col-sm-9">
                  <input type="date" class="form-control" id="editBirthday" v-model="editStudent.birthday"
                    aria-label="First name">
                </div>
              </div>
              <div class="mb-3 row">
                <label for="editEmail" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-9">
                  <input type="email" class="form-control" id="editEmail" v-model="editStudent.email"
                    aria-label="First name">
                </div>
              </div>
              <div class="mb-3 row">
                <label for="editAddress" class="col-sm-3 col-form-label">Address</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" id="editAddress" v-model="editStudent.address"
                    aria-label="First name">
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                  v-on:click="updateStudent">Update</button>
              </div>
            </div>
          </div>
        </div>
      </div> -->
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