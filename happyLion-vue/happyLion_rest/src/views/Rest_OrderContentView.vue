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

  </div>
</template>

<script>
</script>

<style></style>