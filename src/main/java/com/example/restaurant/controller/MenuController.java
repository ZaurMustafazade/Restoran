package com.example.restaurant.controller;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.enums.FoodType;
import com.example.restaurant.model.view.MenuView;
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

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WAITER')")
    @GetMapping
    public ResponseEntity<List<MenuView>> findAll() {
        return ResponseEntity.ok(menuService.getAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<MenuView> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MenuDto> save(@RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuService.create(menuDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> update(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {
        return ResponseEntity.ok(menuService.update(id, menuDto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        menuService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WAITER')")
    @GetMapping("/enums/{foodType}")
    public ResponseEntity<List<Menu>> findAllByFoodType(@RequestParam(name = "foodType") FoodType foodType){
        return ResponseEntity.ok(menuService.findAllByFootType(foodType));
    }

}
