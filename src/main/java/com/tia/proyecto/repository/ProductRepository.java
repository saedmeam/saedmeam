package com.tia.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tia.proyecto.bean.Product;

@Repository

public interface ProductRepository extends JpaRepository<Product, String>{

}
