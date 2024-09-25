package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.controller.RestaurantController;
import fcu.happyLion.happyLion.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

  @Autowired
  DatabaseService dbService;

  // 取得全部restaurant
  public List<Restaurant> getAllRestaurants() {
    List<Restaurant> restaurants = new ArrayList<>();  // 建立陣列

    String sql = "SELECT * FROM Restaurant";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestId(rs.getInt("restaurant_id"));
        restaurant.setName(rs.getString("restaurant_name"));
        restaurant.setTel(rs.getString("restaurant_tel"));
        restaurant.setZipcode(rs.getString("restaurant_zipcode"));
        restaurant.setAddress(rs.getString("restaurant_address"));

        restaurants.add(restaurant);  // 新增到陣列
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return restaurants;  // 回傳陣列
  }


  // 取得 restaurant by ID
  public Restaurant getRestaurantById(int restaurantId) {
    Restaurant restaurant = new Restaurant();

    String sql = "SELECT * FROM Restaurant WHERE restaurant_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, restaurantId);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {  // 如果有找到id, process the first (and only) row
          restaurant.setRestId(rs.getInt("restaurant_id"));
          restaurant.setName(rs.getString("restaurant_name"));
          restaurant.setTel(rs.getString("restaurant_tel"));
          restaurant.setZipcode(rs.getString("restaurant_zipcode"));
          restaurant.setAddress(rs.getString("restaurant_address"));

          return restaurant;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;  // 如果沒找到id
  }


  // 搜尋 restaurant name
  public List<Restaurant> searchRestaurantByName(String keyword) {
    List<Restaurant> restaurants = new ArrayList<>();  // 建立arrayList

    String sql = "SELECT * FROM Restaurant WHERE restaurant_name LIKE ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Restaurant restaurant = new Restaurant();   // 建立物件
          restaurant.setRestId(rs.getInt("restaurant_id"));
          restaurant.setName(rs.getString("restaurant_name"));
          restaurant.setTel(rs.getString("restaurant_tel"));
          restaurant.setZipcode(rs.getString("restaurant_zipcode"));
          restaurant.setAddress(rs.getString("restaurant_address"));

          restaurants.add(restaurant);  // 加入arrayList
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return restaurants;
  }


  // 新增 (INSERT INTO) Restaurant
  public Restaurant createRestaurant(Restaurant restaurant) {
    String sql = "INSERT INTO Restaurant (restaurant_name, restaurant_tel, restaurant_zipcode, restaurant_address) VALUES (?, ?, ?, ?)";

    // key為資料庫自動生成時, Statement.RETURN_GENERATED_KEYS
    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, restaurant.getName());
      pstmt.setString(2, restaurant.getTel());
      pstmt.setString(3, restaurant.getZipcode());
      pstmt.setString(4, restaurant.getAddress());

      pstmt.executeUpdate();  // 執行更新

      try (ResultSet keySet = pstmt.getGeneratedKeys()) {
        if (keySet.next()) {  // 找到的第一個值
          restaurant.setRestId(keySet.getInt(1));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return restaurant;
  }


  // 更新 restaurant
  public Restaurant updateRestaurant(Restaurant restaurant) {
    String sql = "UPDATE Restaurant SET restaurant_name = ?, restaurant_tel = ?, restaurant_zipcode = ?, restaurant_address = ? WHERE restaurant_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, restaurant.getName());
      pstmt.setString(2, restaurant.getTel());
      pstmt.setString(3, restaurant.getZipcode());
      pstmt.setString(4, restaurant.getAddress());
      pstmt.setInt(5, restaurant.getRestId());

      pstmt.executeUpdate();  // 執行更新

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return restaurant;
  }


  // 刪除 restaurant
  public int deleteRestaurant(int id) {
    String sql = "DELETE FROM Restaurant WHERE restaurant_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();   // 執行更新

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return id;
  }
}
