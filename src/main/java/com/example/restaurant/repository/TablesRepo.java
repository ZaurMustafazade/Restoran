package com.example.restaurant.repository;

import com.example.restaurant.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepo extends JpaRepository<Tables,Long> {
}
