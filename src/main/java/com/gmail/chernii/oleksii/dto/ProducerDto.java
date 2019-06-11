package com.gmail.chernii.oleksii.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class ProducerDto {
    private Long id;
    private String name;
    private Set<ProductDto> products;
}
