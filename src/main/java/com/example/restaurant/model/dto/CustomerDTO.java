package com.example.restaurant.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private Long tableId;
}
