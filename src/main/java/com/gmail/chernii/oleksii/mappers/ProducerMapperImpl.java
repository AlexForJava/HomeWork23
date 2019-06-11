package com.gmail.chernii.oleksii.mappers;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;
import com.gmail.chernii.oleksii.enities.ProductEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProducerMapperImpl implements ProducerMapper {
    
    @Override
    public ProducerEntity create(ProducerDto producerDto) {
        return new ProducerEntity()
                .setName(producerDto.getName())
                .setProductEntities(producerDto.getProducts()
                        .stream()
                        .map(productDto -> {
                            ProductEntity productEntity = new ProductEntity()
                                    .setName(productDto.getName())
                                    .setPrice(BigDecimal.valueOf(productDto.getPrice()));
                            productEntity.setUuid(UUID.randomUUID());
                            return productEntity;
                        }).collect(Collectors.toSet()));
    }

    @Override
    public ProducerEntity update(ProducerEntity entity, ProducerDto producerDto) {
        return entity.setName(producerDto.getName())
                .setProductEntities(producerDto.getProducts()
                        .stream()
                        .map(productDto ->
                                new ProductEntity()
                                        .setName(productDto.getName())
                                        .setPrice(BigDecimal.valueOf(productDto.getPrice()))
                        ).collect(Collectors.toSet()));
    }

    @Override
    public ProducerDto entityToDto(ProducerEntity entity) {
        return new ProducerDto()
                .setName(entity.getName())
                .setId(entity.getId())
                .setProducts(entity.getProductEntities()
                        .stream()
                        .map(product -> productEntityToDto(product))
                        .collect(Collectors.toSet()));
    }

    private ProductDto productEntityToDto(ProductEntity entity) {
        return new ProductDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice().intValue());
    }
}
