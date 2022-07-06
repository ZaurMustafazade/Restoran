package com.example.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@javax.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "table_id")
    private List<Customer> customers = new ArrayList<>();
}
