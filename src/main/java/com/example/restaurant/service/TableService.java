package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Table;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.TableDTO;
import com.example.restaurant.model.view.CustomerView;
import com.example.restaurant.model.view.TableView;
import com.example.restaurant.repository.CustomerRepo;
import com.example.restaurant.repository.TableRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    @Autowired
    private  TableRepo tableRepo;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private  CustomerRepo customerRepo;

    public List<TableView> getAll() {
        TypeMap<Table, TableView> typeMapper = modelMapper.getTypeMap(Table.class, TableView.class);
        if (typeMapper == null) { // if not  already added
            typeMapper = modelMapper.createTypeMap(Table.class, TableView.class);
        }

        typeMapper.addMappings(modelMapper->modelMapper.map(src->src.getCustomers(),TableView::setCustomerViews));

        return tableRepo.findAll()
                .stream()
                .map(table -> modelMapper.map(table, TableView.class))
                .collect(Collectors.toList());
    }

    public TableView getById(Long id) {
        var table = tableRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                String.format("Table not found with id - %s", id)
        ));

        var tableView = modelMapper.map(table, TableView.class);
        tableView.setCustomerViews(customerRepo.findAllByTable(table)
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerView.class))
                .collect(Collectors.toList()));

        return tableView;
    }

    public TableDTO create(TableDTO tableDTO) {
        Table table = modelMapper.map(tableDTO, Table.class);
        return modelMapper.map(tableRepo.save(table), TableDTO.class);
    }

    public TableDTO update(Long id, TableDTO tableDTO) {
        Table table = tableRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Table is not found with id - %s", id)
                ));

        modelMapper.map(tableDTO, table, "map");
        table.setId(id);

        return modelMapper.map(tableRepo.save(table), TableDTO.class);
    }

    public void delete(Long id) {
        Table table = tableRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Table is not found with id - %s", id)
                ));
        tableRepo.delete(table);
    }
}
