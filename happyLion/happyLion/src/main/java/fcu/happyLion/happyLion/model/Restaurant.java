package fcu.happyLion.happyLion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.SplittableRandom;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private int restId;
    private String name;
    private String tel;
    private String zipcode;
    private String address;

}
