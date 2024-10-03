package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

  @Autowired
  DatabaseService dbService;

  // 取得全部customer
  public List<Customer> getAllCustomers() {
    List<Customer> customers = new ArrayList<>();  // 建立陣列

    String sql = "SELECT * FROM Customer";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setName(rs.getString("customer_name"));
        customer.setTel(rs.getString("customer_tel"));
        customer.setZipCode(rs.getString("customer_zipcode"));
        customer.setAddress(rs.getString("customer_address"));

        customers.add(customer);  // 新增到陣列
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customers;  // 回傳陣列
  }


  // 取得Customer by ID
  public Customer getCustomerById(int customerId) {
    Customer customer = new Customer();

    String sql = "SELECT * FROM Customer WHERE  customer_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, customerId);   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {  // 如果有找到id, process the first (and only) row
          customer.setCustomerId(rs.getInt("customer_id"));
          customer.setName(rs.getString("customer_name"));
          customer.setTel(rs.getString("customer_tel"));
          customer.setZipCode(rs.getString("customer_zipcode"));
          customer.setAddress(rs.getString("customer_address"));

          return customer;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;  // 如果沒找到id
  }


  // 搜尋 customer name
  public List<Customer> searchCustomerByName(String keyword) {
    List<Customer> customers = new ArrayList<>();  // 建立arrayList

    String sql = "SELECT * FROM Customer WHERE customer_name LIKE ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");   // 執行sql語法

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Customer customer = new Customer();   // 建立物件
          customer.setCustomerId(rs.getInt("customer_id"));
          customer.setName(rs.getString("customer_name"));
          customer.setTel(rs.getString("customer_tel"));
          customer.setZipCode(rs.getString("customer_zipcode"));
          customer.setAddress(rs.getString("customer_address"));

          customers.add(customer);  // 加入arrayList
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }


  // 新增 (INSERT INTO) Customer
  public Customer createCustomer(Customer customer) {
    String sql = "INSERT INTO Customer (customer_name, customer_tel, customer_zipcode, customer_address) VALUES (?, ?, ?, ?)";

    // key為資料庫自動生成時, Statement.RETURN_GENERATED_KEYS
    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setString(1, customer.getName());
      pstmt.setString(2, customer.getTel());
      pstmt.setString(3, customer.getZipCode());
      pstmt.setString(4, customer.getAddress());

      pstmt.executeUpdate();  // 執行更新

      try (ResultSet keySet = pstmt.getGeneratedKeys()) {
        if (keySet.next()) {  // 找到的第一個值
          customer.setCustomerId(keySet.getInt(1));
        }
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return customer;
  }


  // 更新Customer
  public Customer updateCustomer(Customer customer) {
    String sql = "UPDATE Customer SET customer_name = ?, customer_tel = ?, customer_zipcode = ?, customer_address = ? WHERE customer_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, customer.getName());
      pstmt.setString(2, customer.getTel());
      pstmt.setString(3, customer.getZipCode());
      pstmt.setString(4, customer.getAddress());
      pstmt.setInt(5, customer.getCustomerId());

      pstmt.executeUpdate();  // 執行更新

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }


  // 刪除Customer
  public int deleteCustomer(int id) {
    String sql = "DELETE FROM Customer WHERE customer_id = ?";

    try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();   // 執行更新

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return id;
  }
} // end of CustomerService
