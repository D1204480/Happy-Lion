package fcu.happyLion.happyLion.controller;

import fcu.happyLion.happyLion.model.Restaurant;
import fcu.happyLion.happyLion.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/restaurant")
public class RestaurantController {

  @Autowired
  RestaurantService restService;

  @GetMapping("")
  public List<Restaurant> getAllRestaurants() {
    return restService.getAllRestaurants();
  }

  @GetMapping("/{id}")
  public Restaurant getRestaurantById(@PathVariable int id) {
    return restService.getRestaurantById(id);
  }

  @GetMapping("/name/{keyword}")
  public List<Restaurant> searchRestaurantByName(@PathVariable String keyword) {
    return restService.searchRestaurantByName(keyword);
  }

  @PostMapping("")
  public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
    return restService.createRestaurant(restaurant);
  }

  @PutMapping("")
  public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
    return restService.updateRestaurant(restaurant);
  }

  @DeleteMapping("/{id}")
  public int deleteRestaurant(@PathVariable int id) {
    return restService.deleteRestaurant(id);
  }

}
