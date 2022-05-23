package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Orders;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.MenuDto;
import com.example.restaurant.model.dto.OrdersDto;
import com.example.restaurant.repository.MenuRepo;
import com.example.restaurant.repository.OrdersRepo;
import org.aspectj.weaver.ast.Or;
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
public class OrderServiceTest {
    @Mock
    OrdersRepo ordersRepo;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    OrdersService ordersService;

    @Test
    void testMockObjectsAreNotNull() {
        assertNotNull(ordersRepo);
        assertNotNull(modelMapper);
        assertNotNull(ordersService);
    }

    @Test
    void testFindAllSuccess() {
        when(ordersRepo.findAll()).thenReturn(getList());

        List<OrdersDto> ordersDtos = ordersService.getAll();

        assertEquals(ordersDtos.size(), 2);
    }

    @Test
    void findByIdSuccess() {
        Orders orders = new Orders();
        orders.setId(1L);

        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(1L);


        when(ordersRepo.findById(orders.getId())).thenReturn(Optional.of(orders));
        when(ordersService.getById(ordersDto.getId())).thenReturn(ordersDto);

        OrdersDto ordersDto1 = ordersService.getById(ordersDto.getId());

        assertEquals(orders.getId(), ordersDto1.getId());

    }

    @Test
    void testFindByIdNotFoundException() {
        Orders orders = new Orders();
        orders.setId(1L);

        given(ordersRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> ordersService.getById(orders.getId()));
    }

    @Test
    void testDeleteSuccess() {
        Orders orders = new Orders();
        orders.setId(1L);

        when(ordersRepo.findById(orders.getId())).thenReturn(Optional.of(orders));

        ordersService.delete(orders.getId());

        verify(ordersRepo).delete(orders);
    }

    @Test
    void testDeleteNotFound() {
        Orders orders = new Orders();
        orders.setId(1L);


        given(ordersRepo.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> ordersService.delete(orders.getId()));
    }

    @Test
    void testCreateEmployeeSuccess() {
        Orders orders = new Orders();
        orders.setId(1L);

        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(1L);

        when(modelMapper.map(ordersDto, Orders.class)).thenReturn(orders);
        when(ordersRepo.save(orders)).thenReturn(orders);
        when(modelMapper.map(orders, OrdersDto.class)).thenReturn(ordersDto);

        OrdersDto ordersDto1 = ordersService.create(ordersDto);

        assertEquals(ordersDto1.getId(), ordersDto1.getId());
    }
    @Test
    void testUpdateEmployee() {
        Orders orders = new Orders();
        orders.setId(1L);

        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(1L);

        when(ordersRepo.findById(orders.getId())).thenReturn(Optional.of(orders));
        when(ordersService.getById(ordersDto.getId())).thenReturn(ordersDto);

        OrdersDto ordersDto1 = ordersService.update(1L, ordersDto);

        assertEquals(ordersDto1.getId(), ordersDto.getId());
    }
    List<Orders> getList() {
        List<Orders> orders = new ArrayList<>();
        Orders orders1 = new Orders();
        Orders orders2 = new Orders();

        orders.add(orders1);
        orders.add(orders2);

        return orders;
    }

}
