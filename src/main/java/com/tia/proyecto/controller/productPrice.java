package com.tia.proyecto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tia.proyecto.bean.ProductPrice;
import com.tia.proyecto.dto.ProductDTO;
import com.tia.proyecto.exception.ResourceNotFoundException;
import com.tia.proyecto.repository.ProductPriceRepository;

@RestController
@RequestMapping("/api/v1/productPrice")
public class productPrice {

	@Autowired
	private ProductPriceRepository productPriceRepository;

	@GetMapping("/")
	public List<ProductPrice> getAllProducts() {
		return productPriceRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductPrice> getProductPriceById(@PathVariable(value = "id") Integer productId)
			throws ResourceNotFoundException {
		ProductPrice ProductPrice = productPriceRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductPrice not found for this id :: " + productId));
		return ResponseEntity.ok().body(ProductPrice);
	}

	@GetMapping("/{sku}")
	public List<ProductDTO> getProductPriceBySku(@PathVariable(value = "sku") String sku)
			throws ResourceNotFoundException {
		List<ProductPrice> lObjProductPrice = productPriceRepository
				.findAllByProductPricebySKUP(sku != null ? sku : "");
		List<ProductDTO> lObjProductDTO = new ArrayList<>();
		ProductDTO objProductDTO = new ProductDTO();
		for (ProductPrice res : lObjProductPrice) {
			objProductDTO = new ProductDTO();
			objProductDTO.setDes(res.getProduct().getDes());
			objProductDTO.setIncremen(res.getProduct().getIncremen());
			objProductDTO.setIncrementPrice(res.getIncrementPrice());
			objProductDTO.setMaxDuesNo(res.getMaxDuesNo());
			objProductDTO.setPrice(res.getProduct().getPrice());
			objProductDTO.setSku(res.getProduct().getSku());
			lObjProductDTO.add(objProductDTO);
		}
		return lObjProductDTO;
	}

	@PostMapping("/")
	public ProductPrice createProductPrice(@Valid @RequestBody ProductPrice ProductPrice) {
		return productPriceRepository.save(ProductPrice);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductPrice> updateProductPrice(@PathVariable(value = "id") Integer productId,
			@Valid @RequestBody ProductPrice ProductPrice) throws ResourceNotFoundException {
		ProductPrice product = productPriceRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductPrice not found for this id :: " + productId));

		product.setIdProductPrice(ProductPrice.getIdProductPrice());
		product.setIncrementPrice(ProductPrice.getIncrementPrice());
		product.setMaxDuesNo(ProductPrice.getMaxDuesNo());
		final ProductPrice updatedProductPrice = productPriceRepository.save(product);
		return ResponseEntity.ok(updatedProductPrice);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProductPrice(@PathVariable(value = "id") Integer productId)
			throws ResourceNotFoundException {
		ProductPrice ProductPrice = productPriceRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("ProductPrice not found for this id :: " + productId));

		productPriceRepository.delete(ProductPrice);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
