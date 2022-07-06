package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Orders;
import com.example.restaurant.model.dto.OrdersDto;
import com.example.restaurant.model.enums.FoodType;
import com.example.restaurant.model.view.OrderView;
import com.example.restaurant.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrderView>> findAll() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderView> findById(@PathVariable("id") Long id) {
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

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WAITER')")
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderView>> findAllByCustomer_Id(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(ordersService.findAllByCustomer_Id(id));
    }
}
