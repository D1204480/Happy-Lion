package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private int id;
    private String item;
    private int price;
    private int restId;
    private String restName;
    private String description;

}
