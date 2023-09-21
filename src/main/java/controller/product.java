package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bean.Product;
import exception.ResourceNotFoundException;
import repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/product")
public class product {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") String productId)
			throws ResourceNotFoundException {
		Product Product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
		return ResponseEntity.ok().body(Product);
	}

	@PostMapping("/")
	public Product createProduct(@Valid @RequestBody Product Product) {
		return productRepository.save(Product);
	}

	@PostMapping("/cargaExcel/")
	public Product cargaExcel(@Valid @RequestParam("file") MultipartFile file) {
		Product product = new Product();
		List<Product> lproduct = new ArrayList<>();

		InputStream inp;
		Integer iRow = 1;
		try {
			inp = file.getInputStream();
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(iRow); // En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el
											// título de cada columna en la primera fila
			while (row != null) {
				product = new Product();
				Cell cell = row.getCell(0);
				product.setSku(cell.getStringCellValue());
				cell = row.getCell(1);
				product.setDes(cell.getStringCellValue());
				cell = row.getCell(1);

				product.setIncremen(Double.parseDouble(cell.getStringCellValue()));
				cell = row.getCell(1);
				product.setPrice(Double.parseDouble(cell.getStringCellValue()));
				lproduct.add(product);
				iRow++;
				row = sheet.getRow(iRow);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Product res : lproduct) {
			productRepository.save(res);

		}
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") String productId,
			@Valid @RequestBody Product ProductDetails) throws ResourceNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		product.setDes(ProductDetails.getDes());
		product.setIncremen(ProductDetails.getIncremen());
		product.setPrice(ProductDetails.getPrice());
		final Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String productId)
			throws ResourceNotFoundException {
		Product Product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		productRepository.delete(Product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
