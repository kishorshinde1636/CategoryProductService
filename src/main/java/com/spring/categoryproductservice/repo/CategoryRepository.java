package com.spring.categoryproductservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.categoryproductservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
