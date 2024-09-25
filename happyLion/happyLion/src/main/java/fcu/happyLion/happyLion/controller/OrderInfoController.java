package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.OrderInfo;
import fcu.happyLion.happyLion.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin("http://localhost:8082")
@RestController
@RequestMapping("/api/order-info")
public class OrderInfoController {

  @Autowired
  OrderInfoService orderInfoService;

  @GetMapping("")
  public List<OrderInfo> getAllOrderInfos() {
    return orderInfoService.getAllOrderInfos();
  }

  @GetMapping("/{id}")
  public OrderInfo getOrderInfoById(@PathVariable int id) {
    return orderInfoService.getOrderInfoById(id);
  }


  @GetMapping("/date/{date}")
  public List<OrderInfo> searchOrderInfoByDate(@PathVariable Date date) {
    return orderInfoService.searchOrderInfoByDate(date);
  }

  @PostMapping("")
  public OrderInfo createOrderInfo(@RequestBody OrderInfo orderInfo) {
    return orderInfoService.createOrderInfo(orderInfo);
  }

  @PutMapping("")
  public OrderInfo updateOrderInfo(@RequestBody OrderInfo orderInfo) {
    return orderInfoService.updateOrderInfo(orderInfo);
  }

  @DeleteMapping("/{id}")
  public int deleteOrderInfo(@PathVariable int id) {
    return orderInfoService.deleteOrderInfo(id);
  }

} // end of OrderInfoController
