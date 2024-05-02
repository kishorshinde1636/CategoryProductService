package com.spring.categoryproductservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.categoryproductservice.entity.Category;
import com.spring.categoryproductservice.entity.Product;
import com.spring.categoryproductservice.exception.CategoryIdNotFoundException;
import com.spring.categoryproductservice.repo.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(int page, int size) {
		return categoryRepository.findAll(PageRequest.of(page, size));
	}

	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryIdNotFoundException("Category not found with id: " + id));
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category updateCategoryById(Long id, Category category) {
		// Check if a category with the given ID exists
		if (categoryRepository.existsById(id)) {
			// Set the ID of the category to update
			category.setId(id);
			// Save the updated category
			return categoryRepository.save(category);
		} else {
			// If no category with the given ID exists, throw a ResourceNotFoundException
			throw new CategoryIdNotFoundException("Category not found with id: " + id);
		}
	}

	public Category deleteCategoryById(Long id) {
		// Find the category to delete
		Category categoryToDelete = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryIdNotFoundException("Category not found with id: " + id));

		// Get associated products
		List<Product> products = categoryToDelete.getProducts();

		// Disassociate products from the category
		for (Product product : products) {
			product.setCategory(null);
			productService.updateProductById(product.getId(), product); // Update the product
		}

		// Delete the category
		categoryRepository.delete(categoryToDelete);

		// Return the deleted category
		return categoryToDelete;
	}
}
