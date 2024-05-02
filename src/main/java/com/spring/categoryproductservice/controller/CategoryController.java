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

	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "1") int size) {

		Page<Category> categories = categoryService.getAllCategories(page, size);

		if (!categories.isEmpty()) {
			return ResponseEntity.ok(categories);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		Category category = categoryService.getCategoryById(id);

		if (category != null) {
			return ResponseEntity.ok(category);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category createdCategory = categoryService.createCategory(category);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
		Category updatedCategory = categoryService.updateCategoryById(id, category);

		if (updatedCategory != null) {
			return ResponseEntity.ok(updatedCategory);
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
		Category deletedCategory = categoryService.deleteCategoryById(id);

		if (deletedCategory == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(deletedCategory);
	}
}
