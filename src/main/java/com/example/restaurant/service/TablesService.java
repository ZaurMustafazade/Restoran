package com.example.restaurant.service;

import com.example.restaurant.entity.Orders;
import com.example.restaurant.entity.Tables;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.OrdersDto;
import com.example.restaurant.model.dto.TablesDTO;
import com.example.restaurant.repository.TablesRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TablesService {
    private final TablesRepo tablesRepo;
    private final ModelMapper modelMapper;


    public List<TablesDTO> getAll() {
        return tablesRepo.findAll()
                .stream()
                .map(tables -> modelMapper.map(tables,TablesDTO.class))
                .collect(Collectors.toList());
    }

    public TablesDTO getById(Long id) {
        Tables tables = tablesRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Tables not found with id - %s", id)
        ));
        return modelMapper.map(tablesRepo.save(tables), TablesDTO.class);
    }

    public TablesDTO create(TablesDTO tablesDTO) {
        Tables tables = modelMapper.map(tablesDTO, Tables.class);
        return modelMapper.map(tablesRepo.save(tables), TablesDTO.class);
    }

    public TablesDTO update(Long id, TablesDTO tablesDTO) {
        Tables tables = tablesRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Tables not found with id - %s", id)
        ));

        modelMapper.map(tablesDTO, tables, "map");
        tables.setId(id);

        return modelMapper.map(tablesRepo.save(tables), TablesDTO.class);
    }

    public void delete(Long id) {
        Tables tables = tablesRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Tables not found with id - %s", id))
        );
        tablesRepo.delete(tables);
    }

}
