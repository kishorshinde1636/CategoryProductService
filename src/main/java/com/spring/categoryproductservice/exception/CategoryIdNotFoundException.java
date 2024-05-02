package com.spring.categoryproductservice.exception;

import lombok.Getter;

@Getter
public class CategoryIdNotFoundException extends RuntimeException {

	private String message;

	public CategoryIdNotFoundException(String message) {

		this.message = message;
	}
}
