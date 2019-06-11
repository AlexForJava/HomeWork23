package com.gmail.chernii.oleksii.repository;

import com.gmail.chernii.oleksii.enities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
