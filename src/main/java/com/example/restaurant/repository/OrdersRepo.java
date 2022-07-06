package com.example.restaurant.repository;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Orders;
import com.example.restaurant.entity.Table;
import com.example.restaurant.model.enums.FoodType;
import com.example.restaurant.model.view.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders,Long> {
//    List<Orders> findAllByCustomer_Id(Long id);
    List<OrderView> findAllByCustomer_Id(Long id);

}
