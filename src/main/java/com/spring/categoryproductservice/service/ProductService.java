package com.spring.categoryproductservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.categoryproductservice.entity.Category;
import com.spring.categoryproductservice.entity.Product;
import com.spring.categoryproductservice.exception.ProductIdNotFoundException;
import com.spring.categoryproductservice.exception.CategoryIdNotFoundException;
import com.spring.categoryproductservice.repo.CategoryRepository;
import com.spring.categoryproductservice.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	public Product createProduct(Product product) {
		// Check if the category ID provided exists
		Long categoryId = product.getCategory().getId();
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryIdNotFoundException("Category not found with id: " + categoryId));

		// Set the category object's name attribute to the retrieved category's name
		product.getCategory().setName(category.getName());

		return productRepository.save(product);
	}

	public Page<Product> getAllProducts(int page, int size) {
		return productRepository.findAll(PageRequest.of(page, size));
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).map(product -> {
			product.getCategory(); // Fetch associated category details
			return product;
		}).orElseThrow(() -> new ProductIdNotFoundException("Product not found with id: " + id));
	}

	public Product updateProductById(Long id, Product product) {
		// Retrieve the existing product from the database
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new ProductIdNotFoundException("Product not found with id: " + id));

		// Update the fields of the existing product with the values from the updated
		// product
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());

		// If the updated product has a category, update the category field of the
		// existing product
		if (product.getCategory() != null) {
			existingProduct.setCategory(product.getCategory());
		}

		// Save the updated product
		return productRepository.save(existingProduct);
	}

	public Product deleteProductById(Long id) {
		// Find the product to delete
		Product productToDelete = productRepository.findById(id)
				.orElseThrow(() -> new ProductIdNotFoundException("Product not found with id: " + id));

		// Delete the product
		productRepository.deleteById(id);

		// Return the deleted product
		return productToDelete;
	}
}
