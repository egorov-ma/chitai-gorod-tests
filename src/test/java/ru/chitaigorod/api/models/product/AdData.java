package ru.chitaigorod.api.models.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdData{
	@JsonProperty("item_list_name")
	private String itemListName = "product-page";
	@JsonProperty("product_shelf")
	private String productShelf = "";
}