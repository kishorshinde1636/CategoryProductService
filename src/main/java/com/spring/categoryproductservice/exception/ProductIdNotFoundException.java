package com.spring.categoryproductservice.exception;

import lombok.Getter;

@Getter
public class ProductIdNotFoundException extends RuntimeException {

	private String message;

	public ProductIdNotFoundException(String message) {

		this.message = message;
	}

}
