package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.model.OrderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderContentService {

  @Autowired
  DatabaseService dbService;

  // 取得全部 order-content
  public List<OrderContent> getAllOrderContent() {
    List<OrderContent> orderContents = new ArrayList<>();  // 建立陣列

    String sql = "SELECT * FROM Order_content";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        OrderContent orderContent = new OrderContent();
        orderContent.setOrderContentId(rs.getInt("order_content_id"));
        orderContent.setMenuId(rs.getInt("menu_id"));
        orderContent.setQuantity(rs.getInt("quantity"));

        orderContents.add(orderContent);  // 新增到陣列
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return orderContents;  // 回傳陣列
  }


  // 取得 order-content by ID
  public OrderContent getOrderContentById(int id) {
    OrderContent orderContent = new OrderContent();

    String sql = "SELECT * FROM Order_content WHERE order_content_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {  // 如果有找到id, process the first (and only) row
          orderContent.setOrderContentId(rs.getInt("order_content_id"));
          orderContent.setMenuId(rs.getInt("menu_id"));
          orderContent.setQuantity(rs.getInt("quantity"));

          return orderContent;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;  // 如果沒找到id
  }


  // 搜尋 order-content name
  public List<OrderContent> searchMenusByQty(int qty) {
    List<OrderContent> orderContents = new ArrayList<>();  // 建立arrayList

    String sql = "SELECT * FROM Order_content WHERE quantity = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, qty);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          OrderContent orderContent = new OrderContent();   // 建立物件
          orderContent.setOrderContentId(rs.getInt("order_content_id"));
          orderContent.setMenuId(rs.getInt("menu_id"));
          orderContent.setQuantity(rs.getInt("quantity"));

          orderContents.add(orderContent);  // 加入arrayList
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderContents;
  }


  // 新增 (INSERT INTO) order-content
  public OrderContent createOrderContent(OrderContent orderContent) {
    String sql = "INSERT INTO Order_content (menu_id, quantity) VALUES (?, ?)";

    // key為資料庫自動生成時, Statement.RETURN_GENERATED_KEYS
    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, orderContent.getMenuId());
      pstmt.setInt(2, orderContent.getQuantity());

      pstmt.executeUpdate();  // 執行更新

      try (ResultSet keySet = pstmt.getGeneratedKeys()) {
        if (keySet.next()) {  // 找到的第一個值
          orderContent.setMenuId(keySet.getInt(1));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return orderContent;
  }


  // 更新 order-content
  public OrderContent updateOrderContent(OrderContent orderContent) {
    String sql = "UPDATE Order_content SET menu_id =?, quantity = ? WHERE order_content_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, orderContent.getMenuId());
      pstmt.setInt(2, orderContent.getQuantity());
      pstmt.setInt(3, orderContent.getOrderContentId());

      pstmt.executeUpdate();  // 執行更新

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderContent;
  }


  // 刪除 order-content
  public int deleteOrderContent(int id) {
    String sql = "DELETE FROM Order_content WHERE order_content_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();   // 執行更新

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return id;
  }

} // end of OrderContent
