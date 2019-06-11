package com.gmail.chernii.oleksii.mappers;

import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProductEntity;

public interface ProductMapper {
    ProductEntity create(ProductDto productDto);

    ProductEntity update(ProductEntity entity, ProductDto productDto);

    ProductDto entityToDto(ProductEntity entity);
}
