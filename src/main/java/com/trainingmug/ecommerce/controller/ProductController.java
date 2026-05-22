package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.dto.ProductRequestDto;
import com.trainingmug.ecommerce.dto.ProductResponseDto;
import com.trainingmug.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/products")

@RequiredArgsConstructor

public class ProductController {

    private final ProductService service;

    /*
        CREATE PRODUCT
        ADMIN ONLY
     */

    @PostMapping

    public ResponseEntity<ProductResponseDto>
    save(

            @RequestBody
            ProductRequestDto request
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)

                .body(
                        service.save(request)
                );
    }

    /*
        GET ALL PRODUCTS
        CUSTOMER + ADMIN
     */

    @GetMapping

    public ResponseEntity<List<ProductResponseDto>>
    getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

    /*
        GET PRODUCT BY ID
        CUSTOMER + ADMIN
     */

    @GetMapping("/{id}")

    public ResponseEntity<ProductResponseDto>
    getById(

            @PathVariable
            int id
    ) {

        return ResponseEntity.ok(
                service.getById(id)
        );
    }

    /*
        UPDATE PRODUCT
        ADMIN ONLY
     */

    @PutMapping("/{id}")

    public ResponseEntity<ProductResponseDto>
    update(

            @PathVariable
            int id,

            @RequestBody
            ProductRequestDto request
    ) {

        return ResponseEntity.ok(

                service.update(
                        id,
                        request
                )
        );
    }

    /*
        DELETE PRODUCT
        ADMIN ONLY
     */

    @DeleteMapping("/{id}")

    public ResponseEntity<Void>
    deleteById(

            @PathVariable
            int id
    ) {

        service.deleteById(id);

        return ResponseEntity.noContent()

                .build();
    }
}
