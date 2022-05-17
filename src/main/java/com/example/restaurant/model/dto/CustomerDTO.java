package com.example.restaurant.model.dto;

import com.example.restaurant.entity.Tables;
import com.example.restaurant.model.enums.CustomerStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;
    private Long tableId;
    private Long orderId;
    private List<Tables> tables;
}
