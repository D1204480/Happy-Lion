package fcu.happyLion.happyLion.service;

import fcu.happyLion.happyLion.model.Customer;
import fcu.happyLion.happyLion.model.OrderInfoWithCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoWithCustomerService {

    @Autowired
    DatabaseService dbService;

    // 取得全部 order-info
    public List<OrderInfoWithCustomer> getAllOrderInfoWithCutomer() {
        List<OrderInfoWithCustomer> orderInfoWithCustomerList = new ArrayList<>();  // 建立陣列

        /**
         *
         */
        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price, oc.quantity, (quantity * menu_price) AS total_price "
            + "FROM Order_info oi "
            + "JOIN Customer c ON oi.customer_id = c.customer_id  "
            + "JOIN Restaurant r ON oi.restaurant_id = r.restaurant_id "
            + "JOIN Order_content oc ON oi.order_id = oc.order_id "
            + "JOIN Menu m ON m.menu_id = oc.menu_id "
            + "AND oc.menu_id = m.menu_id ";

        try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                OrderInfoWithCustomer orderinfowithcustomer = new OrderInfoWithCustomer();  // 建立物件
                orderinfowithcustomer.setOrderId(rs.getInt("order_id"));
                orderinfowithcustomer.setOrderDate(rs.getDate("order_date"));
                orderinfowithcustomer.setCustomerName(rs.getString("customer_name"));
                orderinfowithcustomer.setRestaurantName(rs.getString("restaurant_name"));
                orderinfowithcustomer.setMenuItem(rs.getString("menu_item"));
                orderinfowithcustomer.setMenuPrice(rs.getInt("menu_price"));
                orderinfowithcustomer.setQuantity(rs.getInt("quantity"));
                orderinfowithcustomer.setTotalPrice(rs.getInt("total_price"));

                orderInfoWithCustomerList.add(orderinfowithcustomer);  // 新增到陣列
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderInfoWithCustomerList;  // 回傳陣列
    }


    // 取得Order-info with Customer by ORDER-ID
    public List<OrderInfoWithCustomer> getOrderInfoWithCustomerById(int customerId) {
        List<OrderInfoWithCustomer> orderInfoWithCustomerList = new ArrayList<>();  // 建立陣列

        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price, oc.quantity, (quantity * menu_price) AS total_price "
            + "FROM Order_info oi "
            + "JOIN Customer c ON oi.customer_id = c.customer_id  "
            + "JOIN Restaurant r ON oi.restaurant_id = r.restaurant_id "
            + "JOIN Order_content oc ON oi.order_id = oc.order_id "
            + "JOIN Menu m ON m.menu_id = oc.menu_id "
            + "AND oi.order_id = ? ";

        try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);   // 執行sql語法

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {  // 如果有找到id, process all rows
                    OrderInfoWithCustomer orderinfowithcustomer = new OrderInfoWithCustomer();  // 建立物件
                    orderinfowithcustomer.setOrderId(rs.getInt("order_id"));
                    orderinfowithcustomer.setOrderDate(rs.getDate("order_date"));
                    orderinfowithcustomer.setCustomerName(rs.getString("customer_name"));
                    orderinfowithcustomer.setRestaurantName(rs.getString("restaurant_name"));
                    orderinfowithcustomer.setMenuItem(rs.getString("menu_item"));
                    orderinfowithcustomer.setMenuPrice(rs.getInt("menu_price"));
                    orderinfowithcustomer.setQuantity(rs.getInt("quantity"));
                    orderinfowithcustomer.setTotalPrice(rs.getInt("total_price"));

                    orderInfoWithCustomerList.add(orderinfowithcustomer);  // 新增到陣列
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoWithCustomerList;  // 回傳陣列
    }


    // 取得Order-info by Restaurant ID
    public List<OrderInfoWithCustomer> getOrderInfoRestaurantById(int restId) {
        List<OrderInfoWithCustomer> orderInfoWithCustomerList = new ArrayList<>();  // 建立陣列

        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price, oc.quantity, (quantity * menu_price) AS total_price "
            + "FROM Order_info oi "
            + "JOIN Customer c ON oi.customer_id = c.customer_id  "
            + "JOIN Restaurant r ON oi.restaurant_id = r.restaurant_id "
            + "JOIN Order_content oc ON oi.order_id = oc.order_id "
            + "JOIN Menu m ON m.menu_id = oc.menu_id "
            + "AND r.restaurant_id = ? ";

        try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, restId);   // 執行sql語法

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {  // 迴圈持續尋找,直到沒有資料
                    OrderInfoWithCustomer orderinfowithcustomer = new OrderInfoWithCustomer();
                    orderinfowithcustomer.setOrderId(rs.getInt("order_id"));
                    orderinfowithcustomer.setOrderDate(rs.getDate("order_date"));
                    orderinfowithcustomer.setCustomerName(rs.getString("customer_name"));
                    orderinfowithcustomer.setRestaurantName(rs.getString("restaurant_name"));
                    orderinfowithcustomer.setMenuItem(rs.getString("menu_item"));
                    orderinfowithcustomer.setMenuPrice(rs.getInt("menu_price"));
                    orderinfowithcustomer.setQuantity(rs.getInt("quantity"));
                    orderinfowithcustomer.setTotalPrice(rs.getInt("total_price"));

                    orderInfoWithCustomerList.add(orderinfowithcustomer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoWithCustomerList;
    }


    // 搜尋 Order-info-with-customer by name
    public List<OrderInfoWithCustomer> searchOrderInfoWithCustomerByName(String keyword) {
        List<OrderInfoWithCustomer> orderInfoWithCustomerList = new ArrayList<>();  // 建立arrayList

        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price, oc.quantity, (quantity * menu_price) AS total_price "
            + "FROM Order_info oi "
            + "JOIN Customer c ON oi.customer_id = c.customer_id  "
            + "JOIN Restaurant r ON oi.restaurant_id = r.restaurant_id "
            + "JOIN Order_content oc ON oi.order_id = oc.order_id "
            + "JOIN Menu m ON m.menu_id = oc.menu_id "
            + "AND customer_name LIKE ?";

        try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1,  "%" + keyword + "%" );   // 執行sql語法

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderInfoWithCustomer orderinfowithcustomer = new OrderInfoWithCustomer();
                    orderinfowithcustomer.setOrderId(rs.getInt("order_id"));
                    orderinfowithcustomer.setOrderDate(rs.getDate("order_date"));
                    orderinfowithcustomer.setCustomerName(rs.getString("customer_name"));
                    orderinfowithcustomer.setRestaurantName(rs.getString("restaurant_name"));
                    orderinfowithcustomer.setMenuItem(rs.getString("menu_item"));
                    orderinfowithcustomer.setMenuPrice(rs.getInt("menu_price"));
                    orderinfowithcustomer.setQuantity(rs.getInt("quantity"));
                    orderinfowithcustomer.setTotalPrice(rs.getInt("total_price"));

                    orderInfoWithCustomerList.add(orderinfowithcustomer);  // 加入arrayList
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoWithCustomerList;
    }


    // 新增 (INSERT INTO) Order-info-with-customer
    public Customer createOrderInfoWithCustomer(Customer customer) {
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

} // end of OrderInfoWithCustomerService
