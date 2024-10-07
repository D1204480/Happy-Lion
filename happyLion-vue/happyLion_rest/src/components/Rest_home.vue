<script setup>
</script>

<template>
  <!-- navigation 顯示區 -->
  <div class="card text-center">
    <div class="card-header">
      <div class="card-body">
        <!-- <h5 class="card-title">{{ currentTitle }}</h5>
        <p class="card-text">{{ currentText }}</p> -->

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
                <tr v-for="restaurant in filteredRestaurants" v-bind:key="restaurant.restId">
                  <td>{{ restaurant.restId }}</td>
                  <td>{{ restaurant.name }}</td>
                  <td>{{ restaurant.tel }}</td>
                  <td>{{ restaurant.zipcode }}</td>
                  <td>{{ restaurant.address }}</td>
                  <td>
                    <button class="btn btn-warning mx-1" data-bs-toggle="modal" data-bs-target="#editModal"
                      @click="onUpdateRestaurant(restaurant)">
                      Edit
                    </button>
                    <button class="btn btn-danger mx-1" data-bs-toggle="modal" data-bs-target="#deleteModal"
                      @click="onSelectRestaurant(restaurant)">
                      Delete
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- Button trigger modal -->
        <!-- <div class="text-center mt-3">
        <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editModal"
          style="margin-right: 0.5em;" v-on:click="onUpdateRestaurant(restaurant)">Edit</button>
        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal"
          v-on:click="onSelectRestaurant(restaurant)">Delete</button>
      </div> -->

        <!-- Delete Modal -->
        <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Delete</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                Are you sure you want to delete this item?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                  v-on:click="deleteRestaurant">Delete</button>
              </div>
            </div>
          </div>
        </div>

        <!-- Edit Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="mb-3 row">
                  <label for="editRestName" class="col-sm-3 col-form-label">餐廳名稱</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editRestName" v-model="editRestaurant.name"
                      aria-label="Restaurant name">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editTel" class="col-sm-3 col-form-label">餐廳電話</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editTel" v-model="editRestaurant.tel"
                      aria-label="Restaurant Tel">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editZip" class="col-sm-3 col-form-label">郵遞區號</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editZip" v-model="editRestaurant.zipcode"
                      aria-label="Zipcode">
                  </div>
                </div>

                <div class="mb-3 row">
                  <label for="editAddress" class="col-sm-3 col-form-label">地址</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editAddress" v-model="editRestaurant.address"
                      aria-label="Address">
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                  v-on:click="updateRestaurant">Update</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <h2>測試: 來自homeView的表單帳號, {{ localUsername }}!</h2>
  </div>
</template>

<script>
export default {
  props: { // props接收只能讀, 值不能被改變
    searchQuery: String,   // 接收navbar的搜尋欄
    username: {
      type: String,
      required: false, // 如果有可能未傳遞，設置為非必需
      default: '' // 設置默認值
    },
    password: {
      type: String,
      required: false,
      default: ''
    }
  },

  data() {
    return {
      localUsername: '', // 本地變數，存放 username
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

      restaurants: [],
      keyword: "",
      selectedRestaurant: {
        restId: "",
        name: "",
        tel: "",
        zipcode: "",
        address: "",
      },
      newRestaurant: {
        restId: "",
        name: "",
        tel: "",
        zipcode: "",
        address: "",
      },
      editRestaurant: {
        restId: "",
        name: "",
        tel: "",
        zipcode: "",
        address: "",
      },
    };
  },

  computed: {
    currentTitle() {
      return this.tabsContent[this.activeTab].title;
    },
    currentText() {
      return this.tabsContent[this.activeTab].text;
    },

    // 過濾餐廳資料
    filteredRestaurants() {
      // 抓取navbar輸入值
      if (this.searchQuery) {
        return this.restaurants.filter(restaurant =>
          restaurant.restId.toString() === (this.searchQuery) ||
          restaurant.name.toLowerCase() === (this.searchQuery.toLowerCase())
        );
      }

      // 從localStorage抓取username
      if (this.localUsername) {
        return this.restaurants.filter(restaurant =>
          restaurant.restId.toString() === (this.localUsername)
        );
      }

      return this.restaurants;
    },
  },

  methods: {
    setActiveTab(tab) {
      this.activeTab = tab; // 切換當前的 tab
    },

    async getData() {
      try {
        let response = await fetch("http://localhost:8080/api/restaurant");
        this.restaurants = await response.json();
        console.log(this.restaurants);
      } catch (error) {
        console.log(error);
      }
    },

    async search() {
      try {
        let response = await fetch("http://localhost:8080/api/restaurant/name/" + this.keyword);
        this.restaurants = await response.json();
        console.log(this.restaurants);
      } catch (error) {
        console.log(error);
      }
    },

    async createRestaurant() {
      try {
        fetch("http://localhost:8080/api/restaurant", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.newRestaurant),
        }).then(() => {
          console.log(response.json);
          this.getData();
        });
      } catch (error) {
        console.log(error);
      }
    },

    async deleteRestaurant() {
      let restId = this.selectedRestaurant.restId;
      try {
        await fetch("http://localhost:8080/api/restaurant/" + restId, {
          method: "DELETE",
        }).then(() => {
          this.getData();   // Refresh data after deletion
        });
      } catch (error) {
        console.log(error);
      }
    },

    onUpdateRestaurant(restaurant) {
      this.editRestaurant = { ...restaurant };
    },

    async updateRestaurant() {
      try {
        await fetch("http://localhost:8080/api/restaurant", {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.editRestaurant),  // Send updated data
        }).then(() => {
          this.getData();   // Fetch updated data to reflect changes in the table
        });
      } catch (error) {
        console.log(error);
      }
    },

    onSelectRestaurant(restaurant) {
      this.selectedRestaurant = restaurant;
    },
  },

  mounted() {
    this.getData();

    // 如果 props.username 為空，從 localStorage 讀取
    if (!this.username) {
      this.localUsername = localStorage.getItem('username') || '';
    } else {
      this.localUsername = this.username; // 如果 props 傳入了 username，將其存到本地變數
    }

    console.log("Received username:", this.localUsername);
    console.log("Received password:", this.password);
    console.log("Received searchQuery:", this.searchQuery);
  },

  created() {   // 頁面一開始就先執行
    // this.getData();
  }
};
</script>

<style>
.nav-link {
  cursor: pointer;
}
</style>