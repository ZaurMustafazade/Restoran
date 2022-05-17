package com.example.restaurant.model.dto;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Menu;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrdersDto {
    private Long id;
    private Long customerId;
    private Long menuId;
    private Long foodId;
    private Long count;
    private Long price;
    private List<Menu> menus;
    private List<Customer> customers;

}
