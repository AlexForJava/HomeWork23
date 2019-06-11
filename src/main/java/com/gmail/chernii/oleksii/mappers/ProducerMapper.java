package com.gmail.chernii.oleksii.mappers;

import com.gmail.chernii.oleksii.dto.ProducerDto;
import com.gmail.chernii.oleksii.enities.ProducerEntity;

public interface ProducerMapper {
    ProducerEntity create(ProducerDto producerDto);

    ProducerEntity update(ProducerEntity entity,ProducerDto producerDto);

    ProducerDto entityToDto(ProducerEntity entity);
}
