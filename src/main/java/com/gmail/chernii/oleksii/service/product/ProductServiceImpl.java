package com.gmail.chernii.oleksii.service.product;

import com.gmail.chernii.oleksii.dto.ProductDto;
import com.gmail.chernii.oleksii.enities.ProductEntity;
import com.gmail.chernii.oleksii.exceptions.NotFoundProductException;
import com.gmail.chernii.oleksii.mappers.product.ProductMapper;
import com.gmail.chernii.oleksii.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public void create(ProductDto productDto) {
        repository.save(mapper.create(productDto));
    }

    @Override
    public void update(ProductDto productDto) throws NotFoundProductException {
        ProductEntity entity = repository.findById(productDto.getId()).orElseThrow(NotFoundProductException::new);
        repository.save(mapper.update(entity, productDto));
    }

    @Override
    public ProductDto findById(Long id) throws NotFoundProductException {
        ProductEntity productEntity = repository.findById(id).orElseThrow(NotFoundProductException::new);
        return mapper.entityToDto(productEntity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDto> findAll() {
        return repository.findAll().stream().map(entity -> mapper.entityToDto(entity)).collect(Collectors.toList());
    }

}
