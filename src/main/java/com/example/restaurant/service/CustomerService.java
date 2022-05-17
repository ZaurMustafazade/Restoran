package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.CustomerDTO;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

    public List<CustomerDTO> getAll() {
        return customerRepo.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer,CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO getById(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Customer not found with id - %s", id)
        ));
        return modelMapper.map(customerRepo.save(customer), CustomerDTO.class);
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return modelMapper.map(customerRepo.save(customer), CustomerDTO.class);
    }

    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Customer not found with id - %s", id)
        ));

        modelMapper.map(customerDTO, customer, "map");
        customer.setId(id);

        return modelMapper.map(customerRepo.save(customer), CustomerDTO.class);
    }

    public void delete(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Customer not found with id - %s", id))
        );
        customerRepo.delete(customer);
    }
}
