package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.Delivery;
import fcu.happyLion.happyLion.service.CustomerService;
import fcu.happyLion.happyLion.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @GetMapping("")
  public List<Delivery> getAllDeliveries() {
    return deliveryService.getAllDeliveries();
  }

  @GetMapping("/{id}")
  public Delivery getDeliveryById(@PathVariable int id) {
    return deliveryService.getDeliveryById(id);
  }

  @GetMapping("/name/{keyword}")
  public List<Delivery> searchDeliveries(@PathVariable String keyword) {
    return deliveryService.searchDeliveriesByName(keyword);
  }

  @PostMapping("")
  public Delivery createDelivery(@RequestBody Delivery delivery) {
    return deliveryService.createDelivery(delivery);
  }

  @PutMapping("")
  public Delivery updateDelivery(@RequestBody Delivery delivery) {
    return deliveryService.updateDelivery(delivery);
  }

  @DeleteMapping("/{id}")
  public int deleteDelivery(@PathVariable int id) {
    return deliveryService.deleteDelivery(id);
  }

} // end of deliveryController