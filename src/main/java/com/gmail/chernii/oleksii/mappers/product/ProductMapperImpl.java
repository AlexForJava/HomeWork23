package com.gmail.chernii.oleksii.mappers.product;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;
import com.gmail.chernii.oleksii.enities.ProductEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProductMapperImpl implements ProductMapper{

    @Override
    public ProductEntity create(ProductDto productDto) {
        ProductEntity entity = new ProductEntity()
                .setName(productDto.getName())
                .setPrice(BigDecimal.valueOf(productDto.getPrice()))
                .setProducer(producerDtotoEntity(productDto.getProducer()));
        entity.setUuid(UUID.randomUUID());
        return entity;
    }

    @Override
    public ProductEntity update(ProductEntity entity, ProductDto productDto) {
        return entity.setName(productDto.getName())
                .setPrice(BigDecimal.valueOf(productDto.getPrice()))
                .setProducer(producerDtotoEntity(productDto.getProducer()));
    }

    @Override
    public ProductDto entityToDto(ProductEntity entity) {
        return new ProductDto()
                .setId(entity.getId())
                .setPrice(entity.getPrice().intValue())
                .setName(entity.getName())
                .setProducer(producerEntityToDto(entity.getProducer()));
    }


    private ProducerEntity producerDtotoEntity(ProducerDto dto) {
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setId(dto.getId());
        producerEntity.setName(dto.getName());
        return producerEntity;
    }

    private ProducerDto producerEntityToDto(ProducerEntity entity) {
        return new ProducerDto()
                .setId(entity.getId())
                .setName(entity.getName());
    }
}
