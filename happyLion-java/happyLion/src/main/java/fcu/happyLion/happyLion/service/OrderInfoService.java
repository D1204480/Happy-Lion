package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoService {

  @Autowired
  DatabaseService dbService;

  // 取得全部 order-info
  public List<OrderInfo> getAllOrderInfos() {
    List<OrderInfo> orderInfos = new ArrayList<>();  // 建立陣列

    String sql = "SELECT * FROM Order_info";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        OrderInfo orderinfo = new OrderInfo();
        orderinfo.setOrderId(rs.getInt("order_id"));
        orderinfo.setOrderDate(rs.getDate("order_date"));
        orderinfo.setRestId(rs.getInt("restaurant_id"));
        orderinfo.setCustomerId(rs.getInt("customer_id"));
        orderinfo.setDeliveryId(rs.getInt("delivery_id"));
        orderinfo.setOrderContentId(rs.getInt("order_content_id"));

        orderInfos.add(orderinfo);  // 新增到陣列
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return orderInfos;  // 回傳陣列
  }


  // 取得 order-info by ID
  public OrderInfo getOrderInfoById(int customerId) {
    OrderInfo orderinfo = new OrderInfo();

    String sql = "SELECT * FROM Order_info WHERE order_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, customerId);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {  // 如果有找到id, process the first (and only) row
          orderinfo.setOrderId(rs.getInt("order_id"));
          orderinfo.setOrderDate(rs.getDate("order_date"));
          orderinfo.setRestId(rs.getInt("restaurant_id"));
          orderinfo.setCustomerId(rs.getInt("customer_id"));
          orderinfo.setDeliveryId(rs.getInt("delivery_id"));
          orderinfo.setOrderContentId(rs.getInt("order_content_id"));

          return orderinfo;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;  // 如果沒找到id
  }


  // 搜尋 order-info name
  public List<OrderInfo> searchOrderInfoByDate(Date date) {
    List<OrderInfo> orderInfos = new ArrayList<>();  // 建立arrayList

    String sql = "SELECT * FROM Order_info WHERE order_date = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setDate(1,  date );   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          OrderInfo orderinfo = new OrderInfo();   // 建立物件
          orderinfo.setOrderId(rs.getInt("order_id"));
          orderinfo.setOrderDate(rs.getDate("order_date"));
          orderinfo.setRestId(rs.getInt("restaurant_id"));
          orderinfo.setCustomerId(rs.getInt("customer_id"));
          orderinfo.setDeliveryId(rs.getInt("delivery_id"));
          orderinfo.setOrderContentId(rs.getInt("order_content_id"));

          orderInfos.add(orderinfo);  // 加入arrayList
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderInfos;
  }


  // 新增 (INSERT INTO) order-info
  public OrderInfo createOrderInfo(OrderInfo orderinfo) {
    String sql = "INSERT INTO Order_info (order_date, restaurant_id, customer_id, delivery_id, order_content_id) VALUES (?, ?, ?, ?, ?)";

    // key為資料庫自動生成時, Statement.RETURN_GENERATED_KEYS
    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setDate(1, orderinfo.getOrderDate());
      pstmt.setInt(2, orderinfo.getRestId());
      pstmt.setInt(3, orderinfo.getCustomerId());
      pstmt.setInt(4, orderinfo.getDeliveryId());
      pstmt.setInt(5, orderinfo.getOrderContentId());

      pstmt.executeUpdate();  // 執行更新

      try (ResultSet keySet = pstmt.getGeneratedKeys()) {
        if (keySet.next()) {  // 找到的第一個值
          orderinfo.setCustomerId(keySet.getInt(1));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return orderinfo;
  }


  // 更新 order-info
  public OrderInfo updateOrderInfo(OrderInfo orderinfo) {
    String sql = "UPDATE Order_info SET order_date = ?, restaurant_id = ?, customer_id = ?, delivery_id = ?, order_content_id = ? WHERE order_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setDate(1, orderinfo.getOrderDate());
      pstmt.setInt(2, orderinfo.getRestId());
      pstmt.setInt(3, orderinfo.getCustomerId());
      pstmt.setInt(4, orderinfo.getDeliveryId());
      pstmt.setInt(5, orderinfo.getOrderContentId());
      pstmt.setInt(6, orderinfo.getOrderId());

      pstmt.executeUpdate();  // 執行更新

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderinfo;
  }


  // 刪除 order-info
  public int deleteOrderInfo(int id) {
    String sql = "DELETE FROM Order_info WHERE order_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();   // 執行更新

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return id;
  }
}
