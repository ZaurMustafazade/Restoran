package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Tables;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.dto.TablesDTO;
import com.example.restaurant.repository.MenuRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @Mock
    MenuRepo menuRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    MenuService menuService;

    @Test
    void testMockObjectsAreNotNull() {
        assertNotNull(menuRepo);
        assertNotNull(modelMapper);
        assertNotNull(menuService);
    }

    @Test
    void testFindAllSuccess() {
        when(menuRepo.findAll()).thenReturn(getList());

        List<MenuDto> menuDtos = menuService.getAll();

        assertEquals(menuDtos.size(), 2);
    }

    @Test
    void findByIdSuccess() {
        Menu menu = new Menu();
        menu.setId(1L);

        MenuDto menuDto = new MenuDto();
        menuDto.setId(1L);


        when(menuRepo.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(menuService.getById(menuDto.getId())).thenReturn(menuDto);

        MenuDto menuDto1 = menuService.getById(menu.getId());

        assertEquals(menu.getId(), menuDto1.getId());

    }

    @Test
    void testFindByIdNotFoundException() {
        Menu menu = new Menu();
        menu.setId(1L);

        given(menuRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> menuService.getById(menu.getId()));
    }

    @Test
    void testDeleteSuccess() {
        Menu menu = new Menu();
        menu.setId(1L);

        when(menuRepo.findById(menu.getId())).thenReturn(Optional.of(menu));

        menuService.delete(menu.getId());

        verify(menuRepo).delete(menu);
    }

    @Test
    void testDeleteNotFound() {
        Menu menu = new Menu();
        menu.setId(1L);


        given(menuRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> menuService.delete(menu.getId()));
    }

    @Test
    void testCreateEmployeeSuccess() {
        Menu menu = new Menu();
        menu.setId(1L);

        MenuDto menuDto = new MenuDto();
        menuDto.setId(1L);

        when(modelMapper.map(menuDto, Menu.class)).thenReturn(menu);
        when(menuRepo.save(menu)).thenReturn(menu);
        when(modelMapper.map(menu, MenuDto.class)).thenReturn(menuDto);

        MenuDto menuDto1 = menuService.create(menuDto);

        assertEquals(menuDto1.getId(), menuDto1.getId());
    }
    @Test
    void testUpdateEmployee() {
        Menu menu = new Menu();
        menu.setId(1L);

        MenuDto menuDto = new MenuDto();
        menuDto.setId(1L);

        when(menuRepo.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(menuService.getById(menuDto.getId())).thenReturn(menuDto);

        MenuDto menuDto1 = menuService.update(1L, menuDto);

        assertEquals(menuDto1.getId(), menuDto.getId());
    }
    List<Menu> getList() {
        List<Menu> positions = new ArrayList<>();
        Menu menu = new Menu();
        Menu menu1 = new Menu();

        positions.add(menu);
        positions.add(menu1);

        return positions;
    }

}



