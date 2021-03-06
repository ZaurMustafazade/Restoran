package com.example.restaurant.model.dto;

import com.example.restaurant.model.enums.FoodType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MenuDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private String foodName;
    private String info;
    private String price;
}
