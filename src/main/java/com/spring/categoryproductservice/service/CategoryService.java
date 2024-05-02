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
		if (categoryRepository.existsById(id)) {
			category.setId(id);
			return categoryRepository.save(category);
		} else {
			throw new CategoryIdNotFoundException("Category not found with id: " + id);
		}
	}

	public Category deleteCategoryById(Long id) {
		Category categoryToDelete = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryIdNotFoundException("Category not found with id: " + id));

		List<Product> products = categoryToDelete.getProducts();

		for (Product product : products) {
			product.setCategory(null);
			productService.updateProductById(product.getId(), product);
		}

		categoryRepository.delete(categoryToDelete);

		return categoryToDelete;
	}
}
