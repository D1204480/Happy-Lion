package fcu.happyLion.happyLion.service;

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
        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price "
                + "FROM Order_info oi, Customer c, Restaurant r, Menu m "
                + "WHERE oi.customer_id = c.customer_id "
                + "AND oi.restaurant_id = r.restaurant_id "
                + "AND r.restaurant_id = m.restaurant_id ";

        try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                OrderInfoWithCustomer orderinfowithcustomer = new OrderInfoWithCustomer();
                orderinfowithcustomer.setOrderId(rs.getInt("order_id"));
                orderinfowithcustomer.setOrderDate(rs.getDate("order_date"));
                orderinfowithcustomer.setCustomerName(rs.getString("customer_name"));
                orderinfowithcustomer.setRestaurantName(rs.getString("restaurant_name"));
                orderinfowithcustomer.setMenuItem(rs.getString("menu_item"));
                orderinfowithcustomer.setMenuPrice(rs.getInt("menu_price"));

                orderInfoWithCustomerList.add(orderinfowithcustomer);  // 新增到陣列
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderInfoWithCustomerList;  // 回傳陣列
    }


    // 取得Order-info with Customer by ID
    public OrderInfoWithCustomer getOrderInfoWithCustomerById(int customerId) {
        OrderInfoWithCustomer orderinfowithcustomer = new OrderInfoWithCustomer();

        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price "
                + "FROM Order_info oi, Customer c, Restaurant r, Menu m "
                + "WHERE oi.customer_id = c.customer_id "
                + "AND oi.restaurant_id = r.restaurant_id "
                + "AND r.restaurant_id = m.restaurant_id "
                + "AND order_id = ?";

        try (Connection connection = dbService.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);   // 執行sql語法

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {  // 如果有找到id, process the first (and only) row
                    orderinfowithcustomer.setOrderId(rs.getInt("order_id"));
                    orderinfowithcustomer.setOrderDate(rs.getDate("order_date"));
                    orderinfowithcustomer.setCustomerName(rs.getString("customer_name"));
                    orderinfowithcustomer.setRestaurantName(rs.getString("restaurant_name"));
                    orderinfowithcustomer.setMenuItem(rs.getString("menu_item"));
                    orderinfowithcustomer.setMenuPrice(rs.getInt("menu_price"));

                    return orderinfowithcustomer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // 如果沒找到id
    }


    // 搜尋 Order-info with customer by name
    public List<OrderInfoWithCustomer> searchOrderInfoWithCustomerByName(String keyword) {
        List<OrderInfoWithCustomer> orderInfoWithCustomerList = new ArrayList<>();  // 建立arrayList

        String sql = "SELECT oi.order_id, oi.order_date, c.customer_name, r.restaurant_name, m.menu_item, m.menu_price "
                + "FROM Order_info oi, Customer c, Restaurant r, Menu m "
                + "WHERE oi.customer_id = c.customer_id "
                + "AND oi.restaurant_id = r.restaurant_id "
                + "AND r.restaurant_id = m.restaurant_id "
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

                    orderInfoWithCustomerList.add(orderinfowithcustomer);  // 加入arrayList
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderInfoWithCustomerList;
    }

} // end of OrderInfoWithCustomerService
