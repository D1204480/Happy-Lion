package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_info {
    private int orderId;
    private String orderDate;
    private int restaurantId;
    private int customerId;
    private int deliveryId;
    private int orderContentId;


}
