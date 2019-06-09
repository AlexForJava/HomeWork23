package com.gmail.chernii.oleksii.enities;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "producers")
public class ProducerEntity extends BaseEntity{

    @Column(name = "producer_name")
    private String name;

    @OneToMany
    private Set<ProductEntity> productEntities;
}
