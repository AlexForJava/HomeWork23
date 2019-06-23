package com.gmail.chernii.oleksii.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private ProducerDto producer;
}
