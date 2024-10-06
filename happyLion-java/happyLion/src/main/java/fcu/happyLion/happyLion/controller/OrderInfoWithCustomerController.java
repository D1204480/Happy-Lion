package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.Customer;
import fcu.happyLion.happyLion.model.OrderInfoWithCustomer;
import fcu.happyLion.happyLion.service.OrderInfoWithCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/order-info-wc")
public class OrderInfoWithCustomerController {

    @Autowired
    OrderInfoWithCustomerService orderInfoWithCustomerService;

    @GetMapping("")
    public List<OrderInfoWithCustomer> getAllOrderInfoWithCustomer() {
        return orderInfoWithCustomerService.getAllOrderInfoWithCutomer();
    }

    // search by order-id
    @GetMapping("/{orderId}")
    public List<OrderInfoWithCustomer> getOrderInfoWithCustomerById(@PathVariable int orderId) {
        return orderInfoWithCustomerService.getOrderInfoWithCustomerById(orderId);
    }

    // search by restaurant-id (僅顯示每筆訂單資訊order-info)
    @GetMapping("/rest-id/{restId}")
    public List<OrderInfoWithCustomer> getOrderInfoRestaurantById(@PathVariable int restId) {
        return orderInfoWithCustomerService.getOrderInfoRestaurantById(restId);
    }

    // search order_content by order-id (顯示每筆訂單資訊的細項order_content)
    @GetMapping("/rest-id/{restId}/{orderId}")
    public List<OrderInfoWithCustomer> getOrderContentByOrderId(@PathVariable int restId, @PathVariable int orderId) {
        return orderInfoWithCustomerService.getOrderContentByOrderId(restId, orderId);
    }

    // search by customer's name
    @GetMapping("/name/{keyword}")
    public List<OrderInfoWithCustomer> searchOrderInfoWithCustomerByName(@PathVariable String keyword) {
        return orderInfoWithCustomerService.searchOrderInfoWithCustomerByName(keyword);
    }

//    @PostMapping("")
//    public OrderInfoWithCustomer createOrderInfoWithCustomer(@RequestBody OrderInfoWithCustomer orderInfoWithCustomer) {
//        return orderInfoWithCustomerService.createOrderInfoWithCustomer(orderInfoWithCustomer);
//    }
//
//    @PutMapping("")
//    public OrderInfoWithCustomer createOrderInfoWithCustomer(@RequestBody OrderInfoWithCustomer orderInfoWithCustomer) {
//        return orderInfoWithCustomerService.updateOrderInfoWithCustomer(orderInfoWithCustomer);
//    }
//
//    @DeleteMapping("/{id}")
//    public int createOrderInfoWithCustomer(@PathVariable int id) {
//        return orderInfoWithCustomerService.deleteOrderInfoWithCustomer(id);
//    }


} // end of OrderInfoWithCustomerController
