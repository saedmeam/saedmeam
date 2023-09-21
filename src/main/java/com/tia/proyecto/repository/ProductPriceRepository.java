package com.tia.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tia.proyecto.bean.ProductPrice;

@Repository

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {

	@Query("Select p from ProductPrice p where p.product.sku = %:strSKU% ")
	List<ProductPrice> findAllByProductPricebySKUP(String strSKU);

}
