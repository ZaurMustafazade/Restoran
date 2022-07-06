package com.example.restaurant.model.view;

import lombok.Data;

@Data
public class OrderView {
    private Long id;
    private Long amount;
    private Long price;
    private Long customerId;
    private Long menuId;

    public OrderView() {
    }
}
