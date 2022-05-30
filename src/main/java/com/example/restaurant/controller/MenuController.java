package com.example.restaurant.controller;

import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.service.MenuService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(tags = "menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<MenuDto>> findAll() {
        return ResponseEntity.ok(menuService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MenuDto> save(@RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuService.create(menuDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> update(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuService.update(id, menuDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        menuService.delete(id);
    }
}
