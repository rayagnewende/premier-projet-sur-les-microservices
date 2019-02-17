package com.micro.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.service.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
   Product findById(int id);
   List<Product> findByPrixGreaterThan(int prixLimit);
   List<Product> findByNomLike(String nom);
}
