package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

  @Autowired
  DatabaseService dbService;

  // 取得全部 delivery
  public List<Delivery> getAllDeliveries() {
    List<Delivery> deliveries = new ArrayList<>();  // 建立陣列

    String sql = "SELECT * FROM Delivery";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(rs.getInt("delivery_id"));
        delivery.setDeliveryName(rs.getString("delivery_name"));
        delivery.setDeliveryTel(rs.getString("delivery_tel"));

        deliveries.add(delivery);  // 新增到陣列
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return deliveries;  // 回傳陣列
  }


  // 取得 delivery by ID
  public Delivery getDeliveryById(int id) {
    Delivery delivery = new Delivery();

    String sql = "SELECT * FROM Delivery WHERE  delivery_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {  // 如果有找到id, process the first (and only) row
          delivery.setDeliveryId(rs.getInt("delivery_id"));
          delivery.setDeliveryName(rs.getString("delivery_name"));
          delivery.setDeliveryTel(rs.getString("delivery_tel"));

          return delivery;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;  // 如果沒找到id
  }


  // 搜尋 delivery name
  public List<Delivery> searchDeliveriesByName(String keyword) {
    List<Delivery> deliveries = new ArrayList<>();  // 建立arrayList

    String sql = "SELECT * FROM Delivery WHERE delivery_name LIKE ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Delivery delivery = new Delivery();   // 建立物件
          delivery.setDeliveryId(rs.getInt("delivery_id"));
          delivery.setDeliveryName(rs.getString("delivery_name"));
          delivery.setDeliveryTel(rs.getString("delivery_tel"));

          deliveries.add(delivery);  // 加入arrayList
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return deliveries;
  }


  // 新增 (INSERT INTO) Delivery
  public Delivery createDelivery(Delivery delivery) {
    String sql = "INSERT INTO Delivery (delivery_name, delivery_tel) VALUES (?, ?)";

    // key為資料庫自動生成時, Statement.RETURN_GENERATED_KEYS
    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, delivery.getDeliveryName());
      pstmt.setString(2, delivery.getDeliveryTel());

      pstmt.executeUpdate();  // 執行更新

      try (ResultSet keySet = pstmt.getGeneratedKeys()) {
        if (keySet.next()) {  // 找到的第一個值
          delivery.setDeliveryId(keySet.getInt(1));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return delivery;
  }


  // 更新 delivery
  public Delivery updateDelivery(Delivery delivery) {
    String sql = "UPDATE Delivery SET delivery_name = ?, delivery_tel = ? WHERE delivery_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, delivery.getDeliveryName());
      pstmt.setString(2, delivery.getDeliveryTel());
      pstmt.setInt(3, delivery.getDeliveryId());

      pstmt.executeUpdate();  // 執行更新

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return delivery;
  }


  // 刪除 delivery
  public int deleteDelivery(int id) {
    String sql = "DELETE FROM Delivery WHERE delivery_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();   // 執行更新

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return id;
  }
}

