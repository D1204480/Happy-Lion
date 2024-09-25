package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoWithCustomer {

    private int orderId;
    private Date orderDate;

    //跨表查詢
    private String customerName;
    private String restaurantName;
    private String menuItem;
    private int menuPrice;



}
