package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Table;
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

    @ManyToOne
    @JsonIgnore
    private Menu menu;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "price")
    private Long price;

    public Long getPrice() {
        return amount*menu.getPrice();
    }
}
