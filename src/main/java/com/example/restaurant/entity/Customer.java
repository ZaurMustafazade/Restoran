package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@javax.persistence.Table(name = "customers")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private List<Orders> orders = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Table table;

    public Customer() {
    }
}
