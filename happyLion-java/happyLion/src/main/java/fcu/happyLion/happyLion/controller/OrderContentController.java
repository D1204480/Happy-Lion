package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.OrderContent;
import fcu.happyLion.happyLion.service.OrderContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-content")
public class OrderContentController {

  @Autowired
  OrderContentService orderContentService;

  @GetMapping("")
  public List<OrderContent> getAllOrderContent() {
    return orderContentService.getAllOrderContent();
  }

  @GetMapping("/{id}")
  public OrderContent getOrderContentById(@PathVariable int id) {
    return orderContentService.getOrderContentById(id);
  }

  @GetMapping("/qty/{qty}")
  public List<OrderContent> searchMenusByQty(@PathVariable int qty) {
    return orderContentService.searchMenusByQty(qty);
  }

  @PostMapping("")
  public OrderContent createOrderContent(@RequestBody OrderContent orderContent) {
    return orderContentService.createOrderContent(orderContent);
  }

  @PutMapping("")
  public OrderContent updateOrderContent(@RequestBody OrderContent orderContent) {
    return orderContentService.updateOrderContent(orderContent);
  }

  @DeleteMapping("/{id}")
  public int deleteOrderContent(@PathVariable int id) {
    return orderContentService.deleteOrderContent(id);
  }
}
