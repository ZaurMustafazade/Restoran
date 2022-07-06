package com.example.restaurant.entity;

import com.example.restaurant.model.enums.FoodType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "menu")
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_type")
    private FoodType foodType;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "info")
    private String info;

    @Column(name = "price")
    private Long price;

    @OneToMany
    @JoinColumn(name = "menu_id")
    private List<Orders> orders = new ArrayList<>();
}
