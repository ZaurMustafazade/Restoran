package com.example.restaurant.repository;

import com.example.restaurant.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepo extends JpaRepository<Table,Long> {
}
