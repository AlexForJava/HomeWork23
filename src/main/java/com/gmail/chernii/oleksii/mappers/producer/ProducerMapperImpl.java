package com.gmail.chernii.oleksii.mappers.producer;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;
import com.gmail.chernii.oleksii.enities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProducerMapperImpl implements ProducerMapper {

    @Override
    public ProducerEntity create(ProducerDto producerDto) {
        ProducerEntity producerEntity = new ProducerEntity().setName(producerDto.getName());
        producerEntity.setUuid(UUID.randomUUID());
        return producerEntity;
        /*return new ProducerEntity()
                .setName(producerDto.getName())
                .setProductEntities(producerDto.getProducts()
                        .stream()
                        .map(productDto -> {
                            ProductEntity productEntity = new ProductEntity()
                                    .setName(productDto.getName())
                                    .setPrice(BigDecimal.valueOf(productDto.getPrice()));
                            productEntity.setUuid(UUID.randomUUID());
                            return productEntity;
                        }).collect(Collectors.toSet()));*/
    }

    @Override
    public ProducerEntity update(ProducerEntity entity, ProducerDto producerDto) {
        entity.setId(producerDto.getId());
        return entity.setName(producerDto.getName());
    }

    @Override
    public ProducerDto entityToDto(ProducerEntity entity) {
        return new ProducerDto()
                .setName(entity.getName())
                .setId(entity.getId());
    }

    private ProductDto productEntityToDto(ProductEntity entity) {
        return new ProductDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice().intValue());
    }
}
