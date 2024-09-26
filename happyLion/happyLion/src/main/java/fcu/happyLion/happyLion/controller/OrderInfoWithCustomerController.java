package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.OrderInfoWithCustomer;
import fcu.happyLion.happyLion.service.OrderInfoWithCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public OrderInfoWithCustomer getOrderInfoWithCustomerById(@PathVariable int orderId) {
        return orderInfoWithCustomerService.getOrderInfoWithCustomerById(orderId);
    }

    // search by restaurant-id
    @GetMapping("/rest/{restId}")
    public OrderInfoWithCustomer getOrderInfoRestaurantById(@PathVariable int restId) {
        return orderInfoWithCustomerService.getOrderInfoRestaurantById(restId);
    }


    // search by customer's name
    @GetMapping("/name/{keyword}")
    public List<OrderInfoWithCustomer> searchOrderInfoWithCustomerByName(@PathVariable String keyword) {
        return orderInfoWithCustomerService.searchOrderInfoWithCustomerByName(keyword);
    }


} // end of OrderInfoWithCustomerController
