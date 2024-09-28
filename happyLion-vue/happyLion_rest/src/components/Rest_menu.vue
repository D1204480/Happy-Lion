<script setup>
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
                <tr v-for="menu in filteredMenuItems" v-bind:key="menu.id">
                  <td>{{ menu.id }}</td>
                  <td>{{ menu.item }}</td>
                  <td>{{ menu.price }}</td>
                  <td>{{ menu.description }}</td>
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
  </div>

</template>

<script>
export default {
  props: {
    searchQuery: String
  },

  data() {
    return {
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
      selectedMenu: {
        id: "",
        item: "",
        price: "",
        description: "",
        restname: "",
      },
      newMenu: {
        id: "",
        item: "",
        price: "",
        description: "",
        restname: "",
      },
      editMenu: {
        id: "",
        item: "",
        price: "",
        description: "",
        restname: "",
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

    // 過濾菜單資料
    filteredMenuItems() {
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
    this.getData();
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