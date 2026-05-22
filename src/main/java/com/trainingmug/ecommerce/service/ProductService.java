package com.trainingmug.ecommerce.service;

import com.trainingmug.ecommerce.dto.ProductRequestDto;
import com.trainingmug.ecommerce.dto.ProductResponseDto;
import com.trainingmug.ecommerce.exception.ProductExistsException;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
     /*
        CREATE PRODUCT
     */

    ProductResponseDto save(

            ProductRequestDto request

    ) throws ProductExistsException;

    /*
        GET ALL PRODUCTS
     */

    List<ProductResponseDto> getAll();

    /*
        GET PRODUCT BY ID
     */

    ProductResponseDto getById(

            int id

    ) throws ProductNotFoundException;

    /*
        UPDATE PRODUCT
     */

    ProductResponseDto update(

            int id,

            ProductRequestDto request

    ) throws ProductNotFoundException;

    /*
        DELETE PRODUCT
     */

    void deleteById(

            int id

    ) throws ProductNotFoundException;
}
