package com.gmail.chernii.oleksii.service.product;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;
import com.gmail.chernii.oleksii.enities.ProductEntity;
import com.gmail.chernii.oleksii.exceptions.NotFoundProductException;
import com.gmail.chernii.oleksii.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public void create(ProductDto productDto) {
        ProductEntity entity = new ProductEntity()
                .setName(productDto.getName())
                .setPrice(BigDecimal.valueOf(productDto.getPrice()))
                .setProducer(producerEntitytoDto(productDto.getProducer()));
        entity.setUuid(UUID.randomUUID());
        repository.save(entity);
    }

    @Override
    public void update(ProductDto productDto) throws NotFoundProductException {
        ProductEntity entity = repository.findById(productDto.getId()).orElseThrow(NotFoundProductException::new)
                .setName(productDto.getName())
                .setPrice(BigDecimal.valueOf(productDto.getPrice()))
                .setProducer(producerEntitytoDto(productDto.getProducer()));
        repository.save(entity);
    }

    @Override
    public ProductDto findById(Long id) throws NotFoundProductException {
        ProductEntity productEntity = repository.findById(id).orElseThrow(NotFoundProductException::new);
        return entityToDto(productEntity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    private ProducerEntity producerEntitytoDto(ProducerDto dto) {
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setId(dto.getId());
        return producerEntity;
    }

    private ProductDto entityToDto(ProductEntity entity) {
        return new ProductDto()
                .setId(entity.getId())
                .setPrice(entity.getPrice().intValue())
                .setName(entity.getName())
                .setProducer(producerEntityToDto(entity.getProducer()));
    }

    private ProducerDto producerEntityToDto(ProducerEntity entity) {
        return new ProducerDto()
                .setId(entity.getId())
                .setName(entity.getName());
    }
}
