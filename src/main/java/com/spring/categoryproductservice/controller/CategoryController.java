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

import com.spring.categoryproductservice.entity.Category;
import com.spring.categoryproductservice.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// GET all categories with pagination
	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "1") int size) {
		// Get paginated categories
		Page<Category> categories = categoryService.getAllCategories(page, size);

		// If categories are found, return them with status code 200 (OK)
		if (!categories.isEmpty()) {
			return ResponseEntity.ok(categories);
		} else {
			// If no categories are found, return status code 204 (No Content)
			return ResponseEntity.noContent().build();
		}
	}

	// GET category by Id
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		// Find the category by id
		Category category = categoryService.getCategoryById(id);

		// If category is found, return it with status code 200 (OK)
		if (category != null) {
			return ResponseEntity.ok(category);
		} else {
			// If category is not found, return status code 404 (Not Found)
			return ResponseEntity.notFound().build();
		}
	}

	// POST create a new category
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		// Create the category
		Category createdCategory = categoryService.createCategory(category);

		// Return the created category with status code 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}

	// PUT update category by Id
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
		// Call the update method in the CategoryService to update the category
		Category updatedCategory = categoryService.updateCategoryById(id, category);

		// If the updatedCategory is not null, return a ResponseEntity with status code
		// 200 (OK) and the updated category in the response body
		if (updatedCategory != null) {
			return ResponseEntity.ok(updatedCategory);
		} else {
			// If the category with the given id is not found, return a ResponseEntity with
			// status code 404 (Not Found)
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
		// Delete the category and get the deleted category
		Category deletedCategory = categoryService.deleteCategoryById(id);

		// Check if the deleted category is null (not found)
		if (deletedCategory == null) {
			// If category is not found, return 404 Not Found status
			return ResponseEntity.notFound().build();
		}

		// If category is found and deleted successfully, return 200 OK status with the
		// deleted category
		return ResponseEntity.ok(deletedCategory);
	}
}
