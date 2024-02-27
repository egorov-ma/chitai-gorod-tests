package ru.chitaigorod.models.search.error;

import lombok.Data;

import java.util.List;

@Data
public class SearchErrorResponseModel {
	private List<ErrorsItem> errors;
}