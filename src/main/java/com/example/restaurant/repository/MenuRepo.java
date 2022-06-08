package com.example.restaurant.repository;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.model.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu,Long> {
//   List<MenuDto> findByFoodType(FoodType foodType);
   List<Menu> findAllByFoodType(FoodType foodType);

//   Menu findMenuByFoodType(FoodType foodType);
}