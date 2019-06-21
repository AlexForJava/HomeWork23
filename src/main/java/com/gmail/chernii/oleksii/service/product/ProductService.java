package com.gmail.chernii.oleksii.service.product;

import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundProductException;

import java.util.List;

public interface ProductService {
    void create(ProductDto productDto);

    void update(ProductDto productDto) throws NotFoundProductException;

    ProductDto findById(Long id) throws NotFoundProductException;

    void deleteById(Long id);

    List<ProductDto> findAll();
}
