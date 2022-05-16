package com.example.restaurant.model.dto;

import com.example.restaurant.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
public class TablesDTO {
    private Long id;
    private String name;
    private Long customerId;
}
