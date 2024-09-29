<script setup>
import Rest_nav_v2 from '../components/Rest_nav_v2.vue'
</script>

<template>
  <!-- navigation 顯示區 -->
  <div class="card text-start">
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
                  <td>
                    <button class="btn btn-warning mx-1" data-bs-toggle="modal" data-bs-target="#editModal"
                      @click="onUpdateOrder(Order)">
                      Edit
                    </button>
                    <button class="btn btn-danger mx-1" data-bs-toggle="modal" data-bs-target="#deleteModal"
                      @click="onSelectOrder(Order)">
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
          style="margin-right: 0.5em;" v-on:click="onUpdateOrder(Order)">Edit</button>
        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal"
          v-on:click="onSelectOrder(Order)">Delete</button>
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
                  <label for="editRestName" class="col-sm-3 col-form-label">餐廳名稱</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editRestName" v-model="editOrder.name"
                      aria-label="Order name">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editTel" class="col-sm-3 col-form-label">餐廳電話</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editTel" v-model="editOrder.tel"
                      aria-label="Order Tel">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editZip" class="col-sm-3 col-form-label">郵遞區號</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editZip" v-model="editOrder.zipcode"
                      aria-label="Zipcode">
                  </div>
                </div>

                <div class="mb-3 row">
                  <label for="editAddress" class="col-sm-3 col-form-label">地址</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editAddress" v-model="editOrder.address"
                      aria-label="Address">
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
  </div>

</template>

<script>
export default {
  props: {
    searchQuery: String,   // 從父組件接收 searchQuery
  },

  data() {
    return {
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
        quantity: "",
        totalPrice: "",
      },
      newOrder: {
        orderId: "",
        orderDate: "",
        customerName: "",
        restaurantName: "",
        menuItem: "",
        menuPrice: "",
        quantity: "",
        totalPrice: "",
      },
      editOrder: {
        orderId: "",
        orderDate: "",
        customerName: "",
        restaurantName: "",
        menuItem: "",
        menuPrice: "",
        quantity: "",
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

    // 過濾訂單資料
    filteredOrders() {
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
        let response = await fetch("http://localhost:8080/api/order-info-wc/rest-id/" + this.searchQuery);
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
    this.getData();  // 頁面加載時顯示所有項目
    console.log("Received searchQuery:", this.searchQuery);
  },

  created() {   // 頁面一開始就先執行
    this.getData();
  }

};
</script>

<style>
.nav-link {
  cursor: pointer;
}
</style>