package com.backend.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.clothing.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
