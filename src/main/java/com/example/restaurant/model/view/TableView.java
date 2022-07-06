package com.example.restaurant.model.view;

import lombok.Data;

import java.util.List;
@Data
public class TableView {
    private Long id;
    private String name;
    private List<CustomerView> customerViews;
}
