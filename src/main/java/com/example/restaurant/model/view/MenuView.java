package com.example.restaurant.model.view;

import com.example.restaurant.model.enums.FoodType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MenuView {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private String foodName;
    private String info;
    private String price;

}
