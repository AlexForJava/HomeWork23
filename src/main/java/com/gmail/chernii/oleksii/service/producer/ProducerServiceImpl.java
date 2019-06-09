package com.gmail.chernii.oleksii.service.producer;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;
import com.gmail.chernii.oleksii.enities.ProductEntity;
import com.gmail.chernii.oleksii.exceptions.NotFoundProducerException;
import com.gmail.chernii.oleksii.repository.ProducerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository repository;

    @Override
    public void create(ProducerDto producerDto) {
        ProducerEntity producerEntity = new ProducerEntity()
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
        repository.save(producerEntity);
    }

    @Override
    public void update(ProducerDto producerDto) throws NotFoundProducerException {
        ProducerEntity producerEntity = repository.findById(producerDto.getId()).orElseThrow(NotFoundProducerException::new)
                .setName(producerDto.getName())
                .setProductEntities(producerDto.getProducts()
                        .stream()
                        .map(productDto ->
                                new ProductEntity()
                                        .setName(productDto.getName())
                                        .setPrice(BigDecimal.valueOf(productDto.getPrice()))
                        ).collect(Collectors.toSet()));
        repository.save(producerEntity);
    }

    @Override
    public ProducerDto findById(Long id) throws NotFoundProducerException {
        return entityToDto(repository.findById(id).orElseThrow(NotFoundProducerException::new));
    }

    @Override
    public List<ProducerDto> findAll() {
        return repository.findAll().stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ProductDto productEntityToDto(ProductEntity entity) {
        return new ProductDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setPrice(entity.getPrice().intValue());
    }

    private ProducerDto entityToDto(ProducerEntity entity) {
        return new ProducerDto()
                .setName(entity.getName())
                .setId(entity.getId())
                .setProducts(entity.getProductEntities()
                        .stream()
                        .map(product -> productEntityToDto(product))
                        .collect(Collectors.toSet()));
    }
}
