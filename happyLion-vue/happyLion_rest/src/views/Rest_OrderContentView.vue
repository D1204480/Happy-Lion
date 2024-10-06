<template>
  <div class="card">
    <div class="card-header container-fluid">
      <div class="card-body d-flex">
        <h3 class="col-6">餐廳名稱: {{ restName }}</h3>
        <h3>餐廳編號: {{ localUsername }}</h3>
      </div>
    </div>

    <!-- table -->
    <div class="card">
      <div class="card-header">
        <div>
          <div class="card-body">
            <table class="table caption-top">
              <caption>訂單詳細內容</caption>
              <thead class="table-warning">
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
                <tr>
                  <th scope="row">1</th>
                  <td>Mark</td>
                  <td>Otto</td>
                  <td>@mdo</td>
                </tr>
                <tr v-for="order in orders" v-bind:key="order.orderId">
                  <th scope="row">{{ order.orderId }}</th>
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
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Delete Modal -->
    <!-- <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true"> -->
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
    <!-- </div> -->

    <!-- Edit Modal -->
    <!-- <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true"> -->
      <!-- <div class="modal-dialog modal-dialog-centered">
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
    <!-- </div> -->


  </div>
</template>

<script>
</script>

<style></style>