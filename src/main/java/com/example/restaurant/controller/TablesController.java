package com.example.restaurant.controller;

import com.example.restaurant.entity.Tables;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.dto.TablesDTO;
import com.example.restaurant.service.TablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TablesController {
    private final TablesService tablesService;

    @GetMapping
    public ResponseEntity<List<TablesDTO>> findAll() {
        return ResponseEntity.ok(tablesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TablesDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tablesService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TablesDTO> save(@RequestBody TablesDTO tablesDTO) {
        return ResponseEntity.ok(tablesService.create(tablesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TablesDTO> update(@PathVariable("id") Long id, @RequestBody TablesDTO tablesDTO) {
        return ResponseEntity.ok(tablesService.update(id, tablesDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        tablesService.delete(id);
    }
}
