package com.example.restaurant.model.view;

import lombok.Data;

@Data
public class MenuView {
    private Long id;
    private String foodType;
    private String foodName;
    private String info;
    private String price;
}
