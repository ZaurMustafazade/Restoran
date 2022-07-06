package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Table;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.CustomerDTO;
import com.example.restaurant.model.view.CustomerView;
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
public class CustomerService {
    @Autowired
    private  CustomerRepo customerRepo;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private  TableRepo tableRepo;

    public List<CustomerView> findAll() {
        TypeMap<Customer, CustomerView> typeMapper = modelMapper.getTypeMap(Customer.class, CustomerView.class);
        if (typeMapper == null) { // if not  already added
            typeMapper = modelMapper.createTypeMap(Customer.class, CustomerView.class);
        }

        typeMapper.addMappings(modelMapper->modelMapper.map(src->src.getTable().getId(),CustomerView::setTableId));

        return customerRepo.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerView.class))
                .collect(Collectors.toList());
    }

    public CustomerView getById(Long id) {
        var customer = customerRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Customer not found with id - %s", id)
                ));

        var customerView = new CustomerView();
        customerView.setTableId(customer.getTable().getId());
        customerView.setIsActive(customer.getIsActive());
        customerView.setCreatedAt(customer.getCreatedAt());
        customerView.setId(customer.getId());

        return customerView;
    }


    public CustomerDTO create(CustomerDTO customerDTO) {
        var table = new Table();
        table.setId(customerDTO.getTableId());

        var customer = new Customer();
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setIsActive(customerDTO.getIsActive());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setTable(table);
        customerRepo.save(customer);

        var customerDTO1 = new CustomerDTO();
        customerDTO1.setId(customerDTO.getId());
        customerDTO1.setCreatedAt(customerDTO.getCreatedAt());
        customerDTO1.setIsActive(customerDTO.getIsActive());
        customerDTO1.setTableId(customerDTO.getTableId());
        return customerDTO1;
    }

    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        var customer = customerRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Customer not found with id - %s", id)
                ));
        modelMapper.map(customerDTO, customer, "map");
        customer.setId(id);
        return modelMapper.map(customerRepo.save(customer), CustomerDTO.class);
    }

    public void delete(Long id) {
        var customer = customerRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Customer not found with id - %s", id))
                );
        customerRepo.delete(customer);
    }


}
