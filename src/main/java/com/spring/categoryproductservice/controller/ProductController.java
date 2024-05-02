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
	
	// POST create a new product
		@PostMapping
		public ResponseEntity<Product> createProduct(@RequestBody Product product) {
			// Call the create method in the ProductService to create a new product
			Product createdProduct = productService.createProduct(product);

			// If the createdProduct is not null, return a ResponseEntity with status code
			// 201 (Created) and the created product
			if (createdProduct != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
			} else {
				// If the createdProduct is null, return a ResponseEntity with status code 400
				// (Bad Request)
				return ResponseEntity.badRequest().build();
			}
		}

	// GET all products with pagination
	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		// Call the findAll method in the ProductService to get all products with
		// pagination
		Page<Product> productsPage = productService.getAllProducts(page, size);

		// If there are products found, return a ResponseEntity with status code 200
		// (OK) and the products page in the response body
		if (!productsPage.isEmpty()) {
			return ResponseEntity.ok(productsPage);
		} else {
			// If there are no products found, return a ResponseEntity with status code 404
			// (Not Found)
			return ResponseEntity.notFound().build();
		}
	}

	// GET product by Id
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		// Call the findById method in the ProductService to get the product by id
		Product product = productService.getProductById(id);

		// If the product is found, return a ResponseEntity with status code 200 (OK)
		// and the product in the response body
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			// If the product is not found, return a ResponseEntity with status code 404
			// (Not Found)
			return ResponseEntity.notFound().build();
		}
	}

	

	// PUT update product by Id
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product product) {
		// Call the update method in the ProductService to update the product
		Product updatedProduct = productService.updateProductById(id, product);

		// If the updatedProduct is not null, return a ResponseEntity with status code
		// 200 (OK) and the updated product
		if (updatedProduct != null) {
			return ResponseEntity.ok(updatedProduct);
		} else {
			// If the updatedProduct is null, return a ResponseEntity with status code 404
			// (Not Found)
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
		// Call the delete method in ProductService
		Product deletedProduct = productService.deleteProductById(id);

		// Return the deleted product with an HTTP status code
		return ResponseEntity.ok(deletedProduct);
	}
}
