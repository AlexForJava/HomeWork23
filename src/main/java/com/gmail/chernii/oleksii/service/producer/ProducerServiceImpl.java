package com.gmail.chernii.oleksii.service.producer;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;
import com.gmail.chernii.oleksii.exceptions.NotFoundProducerException;
import com.gmail.chernii.oleksii.mappers.producer.ProducerMapper;
import com.gmail.chernii.oleksii.repository.ProducerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository repository;
    private final ProducerMapper mapper;

    @Override
    public void create(ProducerDto producerDto) {
        repository.save(mapper.create(producerDto));
    }

    @Override
    public void update(ProducerDto producerDto) throws NotFoundProducerException {
        ProducerEntity producerEntity = repository.findById(producerDto.getId()).orElseThrow(NotFoundProducerException::new);
        repository.save(mapper.update(producerEntity, producerDto));
    }

    @Override
    public ProducerDto findById(Long id) throws NotFoundProducerException {
        return mapper.entityToDto(repository.findById(id).orElseThrow(NotFoundProducerException::new));
    }

    @Override
    public List<ProducerDto> findAll() {
        return repository.findAll().stream().map(entity -> mapper.entityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
