<script setup>
</script>

<template>
  <!-- navigation 顯示區 -->
  <div class="card text-center">
    <div class="card-header">
      <div class="card-body">
        <!-- <h5 class="card-title">{{ currentTitle }}</h5> -->
        <!-- <p class="card-text">{{ currentText }}</p> -->

        <div class="card" style="margin-top: 1.5em;">
          <div class="card-body">
            <!-- 菜單表格 -->
            <table class="table table-hover" v-if="activeTab === 'menu'">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">菜單編號</th>
                  <th scope="col">品項</th>
                  <th scope="col">價格</th>
                  <th scope="col">描述</th>
                  <th scope="col">餐廳名稱</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(menu, index) in filteredMenuItems" v-bind:key="menu.id">
                  <th>{{ index + 1 }}</th> <!-- 顯示序號，從 1 開始 -->
                  <td>{{ menu.id }}</td>
                  <td>{{ menu.item }}</td>
                  <td>{{ menu.price }}</td>
                  <td>{{ menu.description }}</td>
                  <td>{{ menu.restName }}</td>
                  <td>
                    <button class="btn btn-warning mx-1" data-bs-toggle="modal" data-bs-target="#editModal"
                      @click="onUpdateMenu(menu)">
                      Edit
                    </button>
                    <button class="btn btn-danger mx-1" data-bs-toggle="modal" data-bs-target="#deleteModal"
                      @click="onSelectMenu(menu)">
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
          style="margin-right: 0.5em;" v-on:click="onUpdateMenu(Menu)">Edit</button>
        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal"
          v-on:click="onSelectMenu(Menu)">Delete</button>
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
                  v-on:click="deleteMenu">Delete</button>
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
                  <label for="editMenuItem" class="col-sm-3 col-form-label">品項</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editMenuItem" v-model="editMenu.item"
                      aria-label="Menu Item">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editPrice" class="col-sm-3 col-form-label">價格</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editPrice" v-model="editMenu.price"
                      aria-label="Menu Price">
                  </div>
                </div>
                <div class="mb-3 row">
                  <label for="editDescription" class="col-sm-3 col-form-label">描述</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="editDescription" v-model="editMenu.description"
                      aria-label="Description">
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" data-bs-dismiss="modal"
                    v-on:click="updateMenu">Update</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- <h2>測試: 來自homeView的表單帳號, {{ localUsername }}!</h2> -->
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
      activeTab: 'menu', // 預設為菜單
      tabsContent: {
        Menu: {
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

      menuItems: [],
      keyword: "",
      selectedMenu: {   // 選中的菜單
        id: "",
        item: "",
        price: "",
        description: "",
        restName: "",
        restId: "",
      },
      newMenu: {   // 用於新增菜單的資料
        id: "",
        item: "",
        price: "",
        description: "",
        restName: "",
        restId: "",
      },
      editMenu: {   // 用於編輯菜單的資料
        id: "",
        item: "",
        price: "",
        description: "",
        restName: "",
        restId: "",
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

    // 過濾菜單資料 (by menu-id)
    filteredMenuItems() {
      // 從localStorage抓取username, username為餐廳帳號(restId)
      if (this.localUsername) {
        return this.menuItems.filter(item =>
          item.restId == (this.localUsername)
        );
      }

      // 從navbar搜尋欄輸入值做比對
      if (this.searchQuery) {
        return this.menuItems.filter(item =>
          item.id == this.searchQuery);
      }

      return this.menuItems;
    },
  },

  methods: {
    setActiveTab(tab) {
      this.activeTab = tab; // 切換當前的 tab
    },

    async getData() {
      try {
        let response = await fetch("http://localhost:8080/api/menu");
        this.menuItems = await response.json();
        console.log(this.menuItems);
      } catch (error) {
        console.log(error);
      }
    },

    async getDataByRestId() {
      try {
        let response = await fetch("http://localhost:8080/api/menu/rest-id/" + this.localUsername);
        this.menuItems = await response.json();
        console.log(this.menuItems);   // 檢查 API 回傳的資料
      } catch (error) {
        console.log(error);
      }
    },

    async search() {
      try {
        let response = await fetch("http://localhost:8080/api/menu/item/" + this.keyword);
        this.menuItems = await response.json();
        console.log(this.menuItems);
      } catch (error) {
        console.log(error);
      }
    },

    async createMenu() {
      try {
        fetch("http://localhost:8080/api/menu", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.newMenu),
        }).then(() => {
          console.log(response.json);
          this.getData();
        });
      } catch (error) {
        console.log(error);
      }
    },

    onUpdateMenu(menu) {
      this.editMenu = { ...menu };  //{複製物件}
    },

    async updateMenu() {
      try {
        await fetch("http://localhost:8080/api/menu", {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.editMenu),  // Send updated data
        }).then(() => {
          this.getData();   // Fetch updated data to reflect changes in the table
        });
      } catch (error) {
        console.log(error);
      }
    },

    onSelectMenu(menu) {
      this.selectedMenu = menu;
    },

    async deleteMenu() {
      let id = this.selectedMenu.id;
      try {
        await fetch("http://localhost:8080/api/Menu/" + id, {
          method: "DELETE",
        }).then(() => {
          this.getData();   // Refresh data after deletion
        });
      } catch (error) {
        console.log(error);
      }
    },
  },

  mounted() {
    // 獲取初始資料
    // this.getData();   // 頁面加載時顯示所有菜單項目

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