package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.Customer;
import fcu.happyLion.happyLion.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/name/{keyword}")
    public List<Customer> searchCustomersByName(@PathVariable String keyword) {
        return customerService.searchCustomerByName(keyword);
    }

    @PostMapping("")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public int deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

} // end of CustomerController
