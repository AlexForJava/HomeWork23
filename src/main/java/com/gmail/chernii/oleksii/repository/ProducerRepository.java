package com.gmail.chernii.oleksii.repository;

import com.gmail.chernii.oleksii.enities.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerEntity, Long> {
}
