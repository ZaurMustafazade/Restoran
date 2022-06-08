package com.example.restaurant.service;

import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.enums.FoodType;
import com.example.restaurant.repository.MenuRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepo menuRepo;
    private final ModelMapper modelMapper;

    public List<MenuDto> getAll() {
        return menuRepo.findAll()
                .stream()
                .map(menu -> modelMapper.map(menu,MenuDto.class))
                .collect(Collectors.toList());
    }

    public MenuDto getById(Long id) {
        Menu menu = menuRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Menu not found with id - %s", id)
        ));
        return modelMapper.map(menu, MenuDto.class);
    }

    public MenuDto create(MenuDto menuDto) {
        Menu menu = modelMapper.map(menuDto, Menu.class);
        return modelMapper.map(menuRepo.save(menu), MenuDto.class);
    }

    public MenuDto update(Long id, MenuDto menuDto) {
        Menu menu = menuRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Menu not found with id - %s", id)
        ));

        modelMapper.map(menuDto, menu, "map");
        menu.setId(id);

        return modelMapper.map(menuRepo.save(menu), MenuDto.class);
    }

    public void delete(Long id) {
        Menu menu = menuRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Menu not found with id - %s", id))
        );
        menuRepo.delete(menu);
    }
/*
    public  List<MenuDto> findByFoodType(FoodType foodType){
        return menuRepo.findByFoodType(foodType);

    }

    public MenuDto findMenuByFoodType(FoodType foodType){
        Menu menu = menuRepo.findMenuByFoodType(foodType);
        return modelMapper.map(menuRepo.save(menu), MenuDto.class);
    }*/

    public List<Menu> findAllByFootType(FoodType foodType){
        return menuRepo.findAllByFoodType(foodType);
    }
}
