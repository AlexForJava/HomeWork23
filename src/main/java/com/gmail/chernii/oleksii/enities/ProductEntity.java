package com.gmail.chernii.oleksii.enities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producer;
}
