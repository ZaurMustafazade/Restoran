package com.example.restaurant.repository;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    List<Customer> findAllByTable(Table table);
}
