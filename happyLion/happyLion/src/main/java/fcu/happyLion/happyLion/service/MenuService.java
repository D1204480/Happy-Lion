package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

  @Autowired
  DatabaseService dbService;

  // 取得全部 menu
  public List<Menu> getAllMenus() {
    List<Menu> menus = new ArrayList<>();  // 建立陣列

    String sql = "SELECT m.menu_id, m.menu_item, m.menu_price, m.description, r.restaurant_name "
        + "FROM Menu m "
        + "JOIN Restaurant r "
        + "  ON m.restaurant_id = r.restaurant_id; ";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        Menu menu = new Menu();
        menu.setId(rs.getInt("menu_id"));
        menu.setItem(rs.getString("menu_item"));
        menu.setPrice(rs.getInt("menu_price"));
//        menu.setRestId(rs.getInt("restaurant_id"));
        menu.setRestName(rs.getString("restaurant_name"));
        menu.setDescription(rs.getString("description"));

        menus.add(menu);  // 新增到陣列
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return menus;  // 回傳陣列
  }


  // 取得 menu by menu's ID
  public Menu getMenuById(int id) {
    Menu menu = new Menu();

    String sql = "SELECT * FROM Menu WHERE  menu_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {  // 如果有找到id, process the first (and only) row
          menu.setId(rs.getInt("menu_id"));
          menu.setItem(rs.getString("menu_item"));
          menu.setPrice(rs.getInt("menu_price"));
          menu.setRestId(rs.getInt("restaurant_id"));

          return menu;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;  // 如果沒找到id
  }

  // 取得 menu by restaurant's ID
  public List<Menu> getMenuByRestId(int restId) {
    List<Menu> menus = new ArrayList<>();  // 建立陣列

    String sql = "SELECT m.menu_id, m.menu_item, m.menu_price, m.description, r.restaurant_name "
        + "FROM Menu m "
        + "JOIN Restaurant r "
        + "  ON m.restaurant_id = r.restaurant_id "
        + "WHERE r.restaurant_id = ? ;";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, restId);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {  // 迴圈尋找直到沒資料
          Menu menu = new Menu();  // 建立物件
          menu.setId(rs.getInt("menu_id"));
          menu.setItem(rs.getString("menu_item"));
          menu.setPrice(rs.getInt("menu_price"));
          menu.setDescription(rs.getString("description"));
//          menu.setRestId(rs.getInt("restaurant_id"));
          menu.setRestName(rs.getString("restaurant_name"));

          menus.add(menu);  // 新增到陣列
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return menus;  // 回傳陣列
  }


  // 搜尋 menu name
  public List<Menu> searchMenusByItem(String keyword) {
    List<Menu> menus = new ArrayList<>();  // 建立arrayList

    String sql = "SELECT * FROM Menu WHERE menu_item LIKE ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Menu menu = new Menu();   // 建立物件
          menu.setId(rs.getInt("menu_id"));
          menu.setItem(rs.getString("menu_item"));
          menu.setPrice(rs.getInt("menu_price"));
          menu.setRestId(rs.getInt("restaurant_id"));

          menus.add(menu);  // 加入arrayList
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return menus;
  }


  // 新增 (INSERT INTO) Menu
  public Menu createMenu(Menu menu) {
    String sql = "INSERT INTO Menu (menu_item, menu_price, restaurant_id) VALUES (?, ?, ?)";

    // key為資料庫自動生成時, Statement.RETURN_GENERATED_KEYS
    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, menu.getItem());
      pstmt.setInt(2, menu.getPrice());
      pstmt.setInt(3, menu.getRestId());


      pstmt.executeUpdate();  // 執行更新

      try (ResultSet keySet = pstmt.getGeneratedKeys()) {
        if (keySet.next()) {  // 找到的第一個值
          menu.setId(keySet.getInt(1));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return menu;
  }


  // 更新 menu
  public Menu updateMenu(Menu menu) {
    String sql = "UPDATE Menu SET menu_item = ?, menu_price = ?, restaurant_id = ? WHERE menu_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, menu.getItem());
      pstmt.setInt(2, menu.getPrice());
      pstmt.setInt(3, menu.getRestId());
      pstmt.setInt(4, menu.getId());

      pstmt.executeUpdate();  // 執行更新

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return menu;
  }


  // 刪除 menu
  public int deleteMenu(int id) {
    String sql = "DELETE FROM Menu WHERE menu_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();   // 執行更新

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return id;
  }
}
