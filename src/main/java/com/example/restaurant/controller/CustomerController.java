package com.example.restaurant.controller;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.model.dto.CustomerDTO;
import com.example.restaurant.model.view.CustomerView;
import com.example.restaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerView>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerView> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.create(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.update(id, customerDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.delete(id);
    }

}
