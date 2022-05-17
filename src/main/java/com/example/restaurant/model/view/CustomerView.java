package com.example.restaurant.model.view;

import com.example.restaurant.model.enums.CustomerStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class CustomerView {
    private Long id;
    private LocalDateTime createdAt;
    private CustomerStatus customerStatus;
    private Long tableId;
}
