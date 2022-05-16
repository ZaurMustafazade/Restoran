package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Orders;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.dto.OrdersDto;
import com.example.restaurant.repository.MenuRepo;
import com.example.restaurant.repository.OrdersRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepo ordersRepo;
    private final ModelMapper modelMapper;

    public List<OrdersDto> getAll() {
        return ordersRepo.findAll()
                .stream()
                .map(orders -> modelMapper.map(orders,OrdersDto.class))
                .collect(Collectors.toList());
    }

    public OrdersDto getById(Long id) {
        Orders orders = ordersRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Orders not found with id - %s", id)
        ));
        return modelMapper.map(ordersRepo.save(orders), OrdersDto.class);
    }

    public OrdersDto create(OrdersDto ordersDto) {
        Orders orders = modelMapper.map(ordersDto, Orders.class);
        return modelMapper.map(ordersRepo.save(orders), OrdersDto.class);
    }

    public OrdersDto update(Long id, OrdersDto ordersDto) {
        Orders orders = ordersRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Orders not found with id - %s", id)
        ));

        modelMapper.map(ordersDto, orders, "map");
        orders.setId(id);

        return modelMapper.map(ordersRepo.save(orders), OrdersDto.class);
    }

    public void delete(Long id) {
        Orders orders = ordersRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Orders not found with id - %s", id))
        );
        ordersRepo.delete(orders);
    }

}

