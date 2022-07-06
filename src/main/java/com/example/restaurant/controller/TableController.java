package com.example.restaurant.controller;

import com.example.restaurant.entity.Table;
import com.example.restaurant.model.dto.TableDTO;
import com.example.restaurant.model.view.TableView;
import com.example.restaurant.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableView>> findAll() {
        return ResponseEntity.ok(tableService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableView> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tableService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TableDTO> save(@RequestBody TableDTO tableDTO) {
        return ResponseEntity.ok(tableService.create(tableDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDTO> update(@PathVariable("id") Long id, @RequestBody TableDTO tableDTO) {
        return ResponseEntity.ok(tableService.update(id, tableDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        tableService.delete(id);
    }
}
