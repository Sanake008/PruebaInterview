package com.prueba.Prueba.repository;

import com.prueba.Prueba.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface OrderDetailRepository extends JpaRepository<Detail, Integer> {
}
