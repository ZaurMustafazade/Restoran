package com.example.restaurant.service;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Orders;
import com.example.restaurant.entity.Table;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.dto.CustomerDTO;
import com.example.restaurant.model.dto.OrdersDto;
import com.example.restaurant.model.view.CustomerView;
import com.example.restaurant.model.view.OrderView;
import com.example.restaurant.repository.OrdersRepo;
import liquibase.pro.packaged.A;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderView> findAll() {
        TypeMap<Orders, OrderView> typeMapper = modelMapper.getTypeMap(Orders.class, OrderView.class);
        if (typeMapper == null) { // if not  already added
            typeMapper = modelMapper.createTypeMap(Orders.class, OrderView.class);
        }

        typeMapper.addMappings(modelMapper->modelMapper.map(src->src.getMenu().getId(),OrderView::setMenuId));
        typeMapper.addMappings(modelMapper->modelMapper.map(src->src.getCustomer().getId(),OrderView::setCustomerId));

        return ordersRepo.findAll()
                .stream()
                .map(orders -> modelMapper.map(orders, OrderView.class))
                .collect(Collectors.toList());
    }

    public OrderView getById(Long id) {
        var order = ordersRepo
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Customer not found with id - %s", id)
                ));

        var orderView = new OrderView();
        orderView.setId(order.getId());
        orderView.setAmount(order.getAmount());
        orderView.setCustomerId(order.getCustomer().getId());
        orderView.setPrice(order.getPrice());
        orderView.setMenuId(order.getMenu().getId());

        return orderView;
    }

    public OrdersDto create(OrdersDto ordersDto) {
        var menu = new Menu();
        menu.setId(ordersDto.getMenuId());

        var customer = new  Customer();
        customer.setId(ordersDto.getCustomerId());

        var orders = new Orders();
        orders.setAmount(ordersDto.getAmount());
        orders.setPrice(ordersDto.getPrice());
        orders.setCustomer(customer);
        orders.setMenu(menu);
        ordersRepo.save(orders);

        var ordersDto1 = new OrdersDto();
        ordersDto1.setId(ordersDto.getId());
        ordersDto1.setAmount(ordersDto.getAmount());
        ordersDto1.setCustomerId(ordersDto.getCustomerId());
        ordersDto1.setMenuId(ordersDto.getMenuId());

        return ordersDto1;
    }

    public OrdersDto update(Long id, OrdersDto ordersDto) {
        Orders orders = ordersRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Orders not found with id - %s", id)
        ));

        modelMapper.map(ordersDto, orders, "map");
        orders.setId(id);

        return modelMapper.map(ordersRepo.save(orders), OrdersDto.class);
    }

    public void delete(Long id) {
        Orders orders = ordersRepo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Orders not found with id - %s", id))
        );
        ordersRepo.delete(orders);
    }

//    public List<Orders> findAllByCustomer_Id(Long id){
//        return ordersRepo.findAllByCustomer_Id(id);
//    }

    public List<OrderView> findAllByCustomer_Id(Long id){
        TypeMap<Orders, OrderView> typeMapper = modelMapper.getTypeMap(Orders.class, OrderView.class);
        if (typeMapper == null) { // if not  already added
            typeMapper = modelMapper.createTypeMap(Orders.class, OrderView.class);
        }

        typeMapper.addMappings(modelMapper->modelMapper.map(src->src.getMenu().getId(),OrderView::setMenuId));

        return ordersRepo.findAllByCustomer_Id(id)
                .stream()
                .map(orders -> modelMapper.map(orders, OrderView.class))
                .collect(Collectors.toList());
    }

}

