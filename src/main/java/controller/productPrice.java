package controller;

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

import bean.ProductPrice;
import exception.ResourceNotFoundException;
import repository.ProductPriceRepository;

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
