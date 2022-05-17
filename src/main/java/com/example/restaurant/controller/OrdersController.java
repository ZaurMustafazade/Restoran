package com.example.restaurant.controller;

import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.dto.OrdersDto;
import com.example.restaurant.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrdersDto>> findAll() {
        return ResponseEntity.ok(ordersService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ordersService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrdersDto> save(@RequestBody OrdersDto ordersDto) {
        return ResponseEntity.ok(ordersService.create(ordersDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDto> update(@PathVariable("id") Long id, @RequestBody OrdersDto ordersDto) {
        return ResponseEntity.ok(ordersService.update(id, ordersDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        ordersService.delete(id);
    }
}
