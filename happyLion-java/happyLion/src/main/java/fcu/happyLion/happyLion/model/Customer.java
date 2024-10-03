package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int customerId;
    private String name;
    private String tel;
    private String zipCode;
    private String address;

}
