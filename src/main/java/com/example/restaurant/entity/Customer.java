package com.example.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "customers")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_id")
    private Long tableId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JsonIgnore
    private Orders orders;

    @OneToMany
    @JoinColumn(name = "table_id")
    private List<Tables> tables = new ArrayList<>();
}
