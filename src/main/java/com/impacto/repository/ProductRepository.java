package com.impacto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.impacto.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM products p WHERE p.code = :code OR p.code SIMILAR TO CONCAT(:code, '_[0-9]+') ORDER BY p.code DESC LIMIT 1;"
    )
    Optional<Product> findLatestCode(@Param("code") String code);
}
