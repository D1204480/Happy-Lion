package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private int deliveryId;
    private String deliveryName;
    private String deliveryTel;
}
