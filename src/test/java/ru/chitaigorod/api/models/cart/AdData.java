package ru.chitaigorod.api.models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdData {
    @JsonProperty("item_list_name")
    private String itemListName;
    @JsonProperty("product_shelf")
    private String productShelf;
}