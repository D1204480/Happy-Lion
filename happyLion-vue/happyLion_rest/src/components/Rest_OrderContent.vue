<template>
  <div class="card">
    <div class="card-header container-fluid">
      <div class="card-body d-flex">
        <!-- <h3 class="col-6">餐廳名稱: {{ restName }}</h3> -->
        <h3>餐廳編號: {{ localUsername }}</h3>
      </div>
    </div>

    <!-- table -->
    <div class="card">
      <div class="card-header">
        <div>
          <div class="card-body text-center">
            <table class="table caption-top">
              <caption>訂單詳細內容</caption>
              <thead class="table-warning">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">訂單編號</th>
                  <th scope="col">訂單日期</th>
                  <th scope="col">顧客名稱</th>
                  <th scope="col">餐廳名稱</th>
                  <th scope="col">餐點</th>
                  <th scope="col">單價</th>
                  <th scope="col">數量</th>
                  <th scope="col">合計</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(order, index) in orders" v-bind:key="order.orderId">
                  <th>{{ index + 1 }}</th>
                  <td>{{ order.orderId }}</td>
                  <td>{{ order.orderDate }}</td>
                  <td>{{ order.customerName }}</td>
                  <td>{{ order.restaurantName }}</td>
                  <td>{{ order.menuItem }}</td>
                  <td>{{ order.menuPrice }}</td>
                  <td>{{ order.quantity }}</td>
                  <td>{{ order.totalPrice }}</td>
                  <td>
                    <button class="btn btn-warning mx-1" data-bs-toggle="modal" data-bs-target="#editModal"
                      @click="onUpdateOrder(order)">
                      Edit
                    </button>
                    <button class="btn btn-danger mx-1" data-bs-toggle="modal" data-bs-target="#deleteModal"
                      @click="onSelectOrder(order)">
                      Delete
                    </button>
                  </td>
                </tr>

                <!-- 新增總金額row -->
                <tr>
                  <th scope="row" colspan="8" class="text-end">總金額</th>
                  <td>{{ totalAmount }}</td>
                  <td></td>
                </tr>

              </tbody>
            </table>

            <div class="d-flex justify-content-end me-5">
              <router-link to="/rest_order" class="btn btn-success">回到訂單</router-link>
            </div>

          </div>
        </div>
      </div>
    </div>



    <!-- Delete Modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true"> -->
      <!-- <div class="modal-dialog modal-dialog-centered">
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
      </div> -->
    </div>

    <!-- Edit Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true"> -->
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
                <input type="text" class="form-control" id="editCustomerName" v-model="editOrder.customerName"
                  aria-label="OrderCustomerName">
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
      </div> -->
    </div>



    <!-- <h2>測試: 來自homeView的表單帳號, {{ localUsername }}!</h2>
    <h2>測試: 來自orderView的表單帳號, {{ orderId }}!</h2> -->

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
      orderId: '', // 用來儲存從 router 傳遞的 orderId
      totalAmount: 0, // 用來存放總金額
      // activeTab: 'orders', // 預設為orders tab
      // tabsContent: {
      //   Order: {
      //     title: '餐廳名字',
      //     text: '這裡是餐廳的基本資料內容。'
      //   },
      //   menu: {
      //     title: '菜單',
      //     text: '這裡是餐廳的菜單內容。'
      //   },
      //   orders: {
      //     title: '訂單',
      //     text: '這裡是餐廳的訂單內容。'
      //   }
      // },

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

    // 當 orders 變化時，計算總金額
    orders: {
      handler() {
        this.calculateTotalAmount();
      },
      deep: true, // 監聽深層次的變化
    },
  },

  computed: {
    // currentTitle() {
    //   return this.tabsContent[this.activeTab].title;
    // },
    // currentText() {
    //   return this.tabsContent[this.activeTab].text;
    // },

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

    calculateTotalAmount() {
      this.totalAmount = this.orders.reduce((total, order) => {
        return total + order.totalPrice; // 根據你的需求調整這行
      }, 0);
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

    async getOrderDetails(localUsername, orderId) {
      try {
        let response = await fetch(`http://localhost:8080/api/order-info-wc/rest-id/${localUsername}/${orderId}`); // 根據 localUsername 和 orderId 獲取詳細資料
        this.orders = await response.json(); // 假設 API 返回的是該 orderId 的詳細資訊
        console.log(this.orders);
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
    // if (!this.username) {
    //   this.localUsername = localStorage.getItem('username') || '';
    // } else {
    //   this.localUsername = this.username; // 如果 props 傳入了 username，將其存到本地變數
    // }

    // 如果 localUsername 不為空，呼叫 getDataByRestId()
    // if (this.localUsername) {
    //   this.getDataByRestId();
    // }
    console.log("Received username:", this.localUsername);
    console.log("Received password:", this.password);
    console.log("Received searchQuery:", this.searchQuery);


    // 獲取 orderId 和 localUsername
    this.orderId = this.$route.query.orderId;  // 取得路由 query 中的 orderId
    this.localUsername = this.$route.query.localUsername; // 取得路由 query 中的 localUsername

    // 確認 orderId 和 localUsername 是否存在，然後執行相應的邏輯
    if (this.orderId && this.localUsername) {
      console.log("Received orderId:", this.orderId);
      console.log("Received localUsername:", this.localUsername);
      // 呼叫方法獲取該訂單的詳細信息
      this.getOrderDetails(this.localUsername, this.orderId);
    }

    // 獲取數據後可以計算總金額
    this.calculateTotalAmount();
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
