package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Tables;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.dto.TablesDTO;
import com.example.restaurant.repository.MenuRepo;
import com.example.restaurant.repository.TablesRepo;
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
public class TableServiceTest {
    @Mock
    TablesRepo tablesRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    TablesService tablesService;

    @Test
    void testMockObjectsAreNotNull() {
        assertNotNull(tablesRepo);
        assertNotNull(modelMapper);
        assertNotNull(tablesService);
    }

    @Test
    void testFindAllSuccess() {
        when(tablesRepo.findAll()).thenReturn(getList());

        List<TablesDTO> tablesDTOS = tablesService.getAll();

        assertEquals(tablesDTOS.size(), 2);
    }

    @Test
    void findByIdSuccess() {
        Tables tables = new Tables();
        tables.setId(1L);

        TablesDTO tablesDTO = new TablesDTO();
        tablesDTO.setId(1L);


        when(tablesRepo.findById(tables.getId())).thenReturn(Optional.of(tables));
        when(tablesService.getById(tablesDTO.getId())).thenReturn(tablesDTO);

        TablesDTO tablesDTO1 = tablesService.getById(tables.getId());

        assertEquals(tables.getId(), tablesDTO1.getId());

    }

    @Test
    void testFindByIdNotFoundException() {
        Tables tables = new Tables();
        tables.setId(1L);

        given(tablesRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> tablesService.getById(tables.getId()));
    }

    @Test
    void testDeleteSuccess() {
        Tables tables = new Tables();
        tables.setId(1L);

        when(tablesRepo.findById(tables.getId())).thenReturn(Optional.of(tables));

        tablesService.delete(tables.getId());

        verify(tablesRepo).delete(tables);
    }

    @Test
    void testDeleteNotFound() {
        Tables tables = new Tables();
        tables.setId(1L);


        given(tablesRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> tablesService.delete(tables.getId()));
    }

    @Test
    void testCreateEmployeeSuccess() {
        Tables tables = new Tables();
        tables.setId(1L);

        TablesDTO tablesDTO = new TablesDTO();
        tablesDTO.setId(1L);

        when(modelMapper.map(tablesDTO, Tables.class)).thenReturn(tables);
        when(tablesRepo.save(tables)).thenReturn(tables);
        when(modelMapper.map(tables, TablesDTO.class)).thenReturn(tablesDTO);

        TablesDTO tablesDTO1 = tablesService.create(tablesDTO);

        assertEquals(tablesDTO1.getId(), tablesDTO1.getId());
    }
    @Test
    void testUpdateEmployee() {
        Tables tables = new Tables();
        tables.setId(1L);

        TablesDTO tablesDTO = new TablesDTO();
        tablesDTO.setId(1L);

        when(tablesRepo.findById(tables.getId())).thenReturn(Optional.of(tables));
        when(tablesService.getById(tablesDTO.getId())).thenReturn(tablesDTO);

        TablesDTO tablesDTO1 = tablesService.update(1L, tablesDTO);

        assertEquals(tablesDTO1.getId(), tablesDTO.getId());
    }
    List<Tables> getList() {
        List<Tables> table = new ArrayList<>();
        Tables tables = new Tables();
        Tables tables1 = new Tables();

        table.add(tables);
        table.add(tables1);

        return table;
    }

}
