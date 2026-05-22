package com.trainingmug.ecommerce.service.impl;

import com.trainingmug.ecommerce.dto.ProductRequestDto;
import com.trainingmug.ecommerce.dto.ProductResponseDto;
import com.trainingmug.ecommerce.entity.Product;
import com.trainingmug.ecommerce.exception.ProductExistsException;
import com.trainingmug.ecommerce.exception.ProductNotFoundException;
import com.trainingmug.ecommerce.repository.ProductRepository;
import com.trainingmug.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements ProductService {

    private final ProductRepository repository;

    private final ModelMapper modelMapper;

    /*
        CREATE PRODUCT
     */

    @Override
    public ProductResponseDto save(

            ProductRequestDto request

    ) throws ProductExistsException {

        /*
            Check Existing Product
         */

        if(
                repository.existsByName(
                        request.getName()
                )
        ) {

            throw new ProductExistsException(
                    "Product already exists with name : "
                            + request.getName()
            );
        }

        /*
            DTO -> ENTITY
         */

        Product product =
                modelMapper.map(
                        request,
                        Product.class
                );

        /*
            SAVE PRODUCT
         */

        Product savedProduct =
                repository.save(product);

        /*
            ENTITY -> DTO
         */

        return modelMapper.map(
                savedProduct,
                ProductResponseDto.class
        );
    }

    /*
        GET ALL PRODUCTS
     */

    @Override
    public List<ProductResponseDto>
    getAll() {

        return repository.findAll()

                .stream()

                .map(product ->

                        modelMapper.map(
                                product,
                                ProductResponseDto.class
                        )
                )

                .toList();
    }

    /*
        GET PRODUCT BY ID
     */

    @Override
    public ProductResponseDto getById(

            int id

    ) throws ProductNotFoundException {

        Product product =
                repository.findById(id)

                        .orElseThrow(() ->

                                new ProductNotFoundException(
                                        "Product not found with id : "
                                                + id
                                )
                        );

        return modelMapper.map(
                product,
                ProductResponseDto.class
        );
    }

    /*
        UPDATE PRODUCT
     */

    @Override
    public ProductResponseDto update(

            int id,

            ProductRequestDto request

    ) throws ProductNotFoundException {

        Product existingProduct =
                repository.findById(id)

                        .orElseThrow(() ->

                                new ProductNotFoundException(
                                        "Product not found with id : "
                                                + id
                                )
                        );

        /*
            UPDATE FIELDS
         */

        existingProduct.setName(
                request.getName()
        );

        existingProduct.setPrice(
                request.getPrice()
        );

        existingProduct.setCategory(
                request.getCategory()
        );

        existingProduct.setAvailable(
                request.isAvailable()
        );

        Product updatedProduct =
                repository.save(existingProduct);

        return modelMapper.map(
                updatedProduct,
                ProductResponseDto.class
        );
    }

    /*
        DELETE PRODUCT
     */

    @Override
    public void deleteById(

            int id

    ) throws ProductNotFoundException {

        Product product =
                repository.findById(id)

                        .orElseThrow(() ->

                                new ProductNotFoundException(
                                        "Product not found with id : "
                                                + id
                                )
                        );

        repository.delete(product);
    }
}

