package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "menyu")
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "info")
    private String info;

    @Column(name = "price")
    private String price;

    @ManyToOne
    @JsonIgnore
    private Orders orders;
}
