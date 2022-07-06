//package com.example.restaurant.service;
//
//import com.example.restaurant.entity.Customer;
//import com.example.restaurant.entity.Menu;
//import com.example.restaurant.exception.NotFoundException;
//import com.example.restaurant.model.dto.CustomerDTO;
//import com.example.restaurant.model.dto.MenuDto;
//import com.example.restaurant.repository.CustomerRepo;
//import com.example.restaurant.repository.MenuRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CustomerServiceTest {
//
//    @Mock
//    CustomerRepo customerRepo;
//
//    @Mock
//    ModelMapper modelMapper;
//
//    @InjectMocks
//    CustomerService customerService;
//
//    @Test
//    void testMockObjectsAreNotNull() {
//        assertNotNull(customerRepo);
//        assertNotNull(modelMapper);
//        assertNotNull(customerService);
//    }
//
//    @Test
//    void testFindAllSuccess() {
//        when(customerRepo.findAll()).thenReturn(getList());
//
//        List<CustomerDTO> customerDTOS = customerService.getAll();
//
//        assertEquals(customerDTOS.size(), 2);
//    }
//
//    @Test
//    void findByIdSuccess() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1L);
//
//
//        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));
//        when(customerService.getById(customerDTO.getId())).thenReturn(customerDTO);
//
//        CustomerDTO customerDTO1 = customerService.getById(customer.getId());
//
//        assertEquals(customer.getId(), customerDTO1.getId());
//
//    }
//
//    @Test
//    void testFindByIdNotFoundException() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        given(customerRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));
//
//        assertThrows(NotFoundException.class, () -> customerService.getById(customer.getId()));
//    }
//
//    @Test
//    void testDeleteSuccess() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));
//
//        customerService.delete(customer.getId());
//
//        verify(customerRepo).delete(customer);
//    }
//
//    @Test
//    void testDeleteNotFound() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//
//        given(customerRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));
//
//        assertThrows(NotFoundException.class, () -> customerService.delete(customer.getId()));
//    }
//
//    @Test
//    void testCreateEmployeeSuccess() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1L);
//
//        when(modelMapper.map(customerDTO, Customer.class)).thenReturn(customer);
//        when(customerRepo.save(customer)).thenReturn(customer);
//        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(customerDTO);
//
//        CustomerDTO customerDTO1 = customerService.create(customerDTO);
//
//        assertEquals(customerDTO1.getId(), customerDTO1.getId());
//    }
//    @Test
//    void testUpdateEmployee() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(1L);
//
//        when(customerRepo.findById(customer.getId())).thenReturn(Optional.of(customer));
//        when(customerService.getById(customerDTO.getId())).thenReturn(customerDTO);
//
//        CustomerDTO customerDTO1 = customerService.update(1L, customerDTO);
//
//        assertEquals(customerDTO1.getId(), customerDTO.getId());
//    }
//    List<Customer> getList() {
//        List<Customer> customers = new ArrayList<>();
//        Customer customer = new Customer();
//        Customer customer1 = new Customer();
//
//        customers.add(customer);
//        customers.add(customer1);
//
//        return customers;
//    }
//}
