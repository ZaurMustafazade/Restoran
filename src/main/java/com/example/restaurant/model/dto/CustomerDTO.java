package com.example.restaurant.model.dto;

import com.example.restaurant.entity.Tables;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private Long tableId;
    private Long orderId;
    private List<Tables> tables;
}
