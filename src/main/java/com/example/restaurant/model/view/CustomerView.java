package com.example.restaurant.model.view;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerView {
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private Long tableId;

    public CustomerView() {
    }
}
