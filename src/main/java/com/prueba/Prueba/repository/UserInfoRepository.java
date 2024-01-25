package com.prueba.Prueba.repository;

import com.prueba.Prueba.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByName(String username);
}

