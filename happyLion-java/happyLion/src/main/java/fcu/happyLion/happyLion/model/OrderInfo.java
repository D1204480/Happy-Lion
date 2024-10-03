package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private int orderId;
    private Date orderDate;
    private int restId;
    private int customerId;
    private int deliveryId;
    private int orderContentId;
}
