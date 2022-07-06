package com.example.restaurant.model.dto;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDto {
    private Long id;
    private Long amount;
    private Long price;
    private Long customerId;
    private Long menuId;

}
