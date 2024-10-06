<script setup>
import Rest_nav_v2 from '../components/Rest_nav_v2.vue'
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

            <!-- 訂單表格 -->
            <table class="table table-hover" v-if="activeTab === 'orders'">
              <thead>
                <tr>
                  <th scope="col">訂單編號</th>
                  <th scope="col">訂單日期</th>
                  <th scope="col">顧客名稱</th>
                  <th scope="col">餐廳名稱</th>
                  <!-- <th scope="col">餐點</th>
                  <th scope="col">單價</th> -->
                  <th scope="col">總數量</th>
                  <th scope="col">總金額</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="order in orders" v-bind:key="order.orderId">
                  <td>{{ order.orderId }}</td>
                  <td>{{ order.orderDate }}</td>
                  <td>{{ order.customerName }}</td>
                  <td>{{ order.restaurantName }}</td>
                  <!-- <td>{{ order.menuItem }}</td>
                  <td>{{ order.menuPrice }}</td> -->
                  <td>{{ order.totalQuantity }}</td>
                  <td>{{ order.totalPrice }}</td>
                  <td>
                    <!-- 跳轉到訂單品項頁面, 傳遞 orderId 作為查詢參數 -->
                    <router-link :to="{ path: '/rest_order_content', query: { orderId: order.orderId, localUsername: localUsername } }" class="btn btn-success">詳細內容</router-link>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

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
                  v-on:click="deleteOrder">Delete</button>
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
                  <label for="editOrderDate" class="col-sm-3 col-form-label">訂單日期</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editOrderDate" v-model="editOrder.orderDate"
                      aria-label="OrderDate">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editCustomerName" class="col-sm-3 col-form-label">顧客名稱</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editCustomerName" v-model="editOrder.customerName" aria-label="OrderCustomerName">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editRestName" class="col-sm-3 col-form-label">餐廳名稱</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editRestName" v-model="editOrder.restaurantName"
                      aria-label="Zipcode">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editMenuItem" class="col-sm-3 col-form-label">餐點</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editMenuItem" v-model="editOrder.menuItem"
                      aria-label="MenuItem">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editMenuPrice" class="col-sm-3 col-form-label">單價</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editMenuPrice" v-model="editOrder.menuPrice"
                      aria-label="MenuPrice">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editQuantity" class="col-sm-3 col-form-label">數量</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editQuantity" v-model="editOrder.quantity"
                      aria-label="quantity">
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                  v-on:click="updateOrder">Update</button>
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
      activeTab: 'orders', // 預設為orders tab
      tabsContent: {
        Order: {
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

      orders: [],
      keyword: "",
      selectedOrder: {
        orderId: "",
        orderDate: "",
        customerName: "",
        restaurantName: "",
        menuItem: "",
        menuPrice: "",
        totalQuantity: "",
        totalPrice: "",
      },
      newOrder: {
        orderId: "",
        orderDate: "",
        customerName: "",
        restaurantName: "",
        menuItem: "",
        menuPrice: "",
        totalQuantity: "",
        totalPrice: "",
      },
      editOrder: {
        orderId: "",
        orderDate: "",
        customerName: "",
        restaurantName: "",
        menuItem: "",
        menuPrice: "",
        totalQuantity: "",
        totalPrice: "",
      },
    };
  },

  watch: {
    // 監聽 searchQuery 變化，當 searchQuery 有更新時觸發 getDatabyRestId
    searchQuery(newValue) {
      if (newValue) {
        this.getDataByRestId(); // 呼叫 API 獲取資料
      }
    },
  },

  computed: {
    currentTitle() {
      return this.tabsContent[this.activeTab].title;
    },
    currentText() {
      return this.tabsContent[this.activeTab].text;
    },

    // 過濾訂單資料 (by order-id)
    filteredOrders() {
      // 從localStorage抓取username, username為餐廳帳號(restId)
      if (this.localUsername) {
        return this.orders.filter(order =>
          order.restId == (this.localUsername)
        );
      }

      if (this.searchQuery) {
        return this.orders.filter(order =>
          order.orderId == this.searchQuery);
      }
      return this.orders;
    }
  },

  methods: {
    setActiveTab(tab) {
      this.activeTab = tab; // 切換當前的 tab
    },

    async getData() {
      try {
        let response = await fetch("http://localhost:8080/api/order-info-wc");
        this.orders = await response.json();
        console.log(this.orders);
      } catch (error) {
        console.log(error);
      }
    },

    async getDataByRestId() {
      try {
        let response = await fetch("http://localhost:8080/api/order-info-wc/rest-id/" + this.localUsername);
        this.orders = await response.json();
        console.log(this.orders);   // 檢查 API 回傳的資料
      } catch (error) {
        console.log(error);
      }
    },

    async search() {
      try {
        let response = await fetch("http://localhost:8080/api/order-info/name" + this.keyword);
        this.orders = await response.json();
        console.log(this.orders);
      } catch (error) {
        console.log(error);
      }
    },

    async createOrder() {
      try {
        fetch("http://localhost:8080/api/order-info-wc", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.newOrder),
        }).then(() => {
          console.log(response.json);
          this.getData();
        });
      } catch (error) {
        console.log(error);
      }
    },

    async deleteOrder() {
      let orderId = this.selectedOrder.orderId;
      try {
        await fetch("http://localhost:8080/api/order-info-wc/" + orderId, {
          method: "DELETE",
        }).then(() => {
          this.getData();   // Refresh data after deletion
        });
      } catch (error) {
        console.log(error);
      }
    },

    onUpdateOrder(order) {
      this.editOrder = { ...order };
    },

    async updateOrder() {
      try {
        await fetch("http://localhost:8080/api/order-info-wc", {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.editOrder),  // Send updated data
        }).then(() => {
          this.getData();   // Fetch updated data to reflect changes in the table
        });
      } catch (error) {
        console.log(error);
      }
    },

    onSelectOrder(order) {
      this.selectedOrder = order;
    },
  },

  mounted() {
    // 獲取初始資料
    // this.getData();  // 頁面加載時顯示所有項目

    // 如果 props.username 為空，從 localStorage 讀取
    if (!this.username) {
      this.localUsername = localStorage.getItem('username') || '';
    } else {
      this.localUsername = this.username; // 如果 props 傳入了 username，將其存到本地變數
    }

    // 如果 localUsername 不為空，呼叫 getDataByRestId()
    if (this.localUsername) {
      this.getDataByRestId();
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