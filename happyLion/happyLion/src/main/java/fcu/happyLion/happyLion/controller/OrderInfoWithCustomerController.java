package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.OrderInfoWithCustomer;
import fcu.happyLion.happyLion.service.OrderInfoWithCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-info-with-customer")
public class OrderInfoWithCustomerController {

    @Autowired
    OrderInfoWithCustomerService orderInfoWithCustomerService;

    @GetMapping("")
    public List<OrderInfoWithCustomer> getAllOrderInfoWithCustomer() {
        return orderInfoWithCustomerService.getAllOrderInfoWithCutomer();
    }

    @GetMapping("/{id}")
    public OrderInfoWithCustomer getOrderInfoWithCustomerById(@PathVariable int id) {
        return orderInfoWithCustomerService.getOrderInfoWithCustomerById(id);
    }


    @GetMapping("/name/{keyword}")
    public List<OrderInfoWithCustomer> searchOrderInfoWithCustomerByName(@PathVariable String keyword) {
        return orderInfoWithCustomerService.searchOrderInfoWithCustomerByName(keyword);
    }


} // end of OrderInfoWithCustomerController
