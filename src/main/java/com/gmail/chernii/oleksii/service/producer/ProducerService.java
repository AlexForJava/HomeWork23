package com.gmail.chernii.oleksii.service.producer;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundProducerException;

import java.util.List;

public interface ProducerService {
    void create(ProducerDto producerDto);

    void update(ProducerDto producerDto) throws NotFoundProducerException;

    ProducerDto findById(Long id) throws NotFoundProducerException;

    List<ProducerDto> findAll();

    void deleteById(Long id);
}
