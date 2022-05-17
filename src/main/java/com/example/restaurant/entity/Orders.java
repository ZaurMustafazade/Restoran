package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "orders")
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "food_id")
    private Long foodId;

    @Column(name = "count")
    private Long count;

    @Column(name = "price")
    private Long price;

    @OneToMany
    @JoinColumn(name = "food_id")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Customer> customers = new ArrayList<>();
}
