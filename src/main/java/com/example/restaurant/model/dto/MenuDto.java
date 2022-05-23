package com.example.restaurant.model.dto;

import com.example.restaurant.entity.Orders;
import com.example.restaurant.model.enums.FoodType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class MenuDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private String foodName;
    private String info;
    private String price;
    private Long ordersId;
    private Long foodId;
}
