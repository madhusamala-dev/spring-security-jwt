package com.trainingmug.ecommerce.dto;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;

    private double price;

    private String category;

    private boolean isAvailable;
}
