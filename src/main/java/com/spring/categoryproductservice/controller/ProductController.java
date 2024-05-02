package com.spring.categoryproductservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.spring.categoryproductservice.entity.Product;
import com.spring.categoryproductservice.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		Product createdProduct = productService.createProduct(product);

		if (createdProduct != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
		} else {

			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Page<Product> productsPage = productService.getAllProducts(page, size);

		if (!productsPage.isEmpty()) {
			return ResponseEntity.ok(productsPage);
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {

		Product product = productService.getProductById(id);

		if (product != null) {
			return ResponseEntity.ok(product);
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product product) {

		Product updatedProduct = productService.updateProductById(id, product);

		if (updatedProduct != null) {
			return ResponseEntity.ok(updatedProduct);
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {

		Product deletedProduct = productService.deleteProductById(id);

		return ResponseEntity.ok(deletedProduct);
	}
}
