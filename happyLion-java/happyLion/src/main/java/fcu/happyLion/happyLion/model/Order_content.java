package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_content {
    private int orderContentId;
    private int menuId;
    private int quantity;
}
