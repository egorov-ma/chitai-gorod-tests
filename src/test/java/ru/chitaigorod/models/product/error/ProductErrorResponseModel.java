package ru.chitaigorod.models.product.error;

import lombok.Data;

@Data
public class ProductErrorResponseModel {
	private String requestId;
	private String message;
}