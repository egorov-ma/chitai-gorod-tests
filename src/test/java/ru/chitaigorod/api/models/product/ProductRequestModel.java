package ru.chitaigorod.api.models.product;

import lombok.Data;

@Data
public class ProductRequestModel {
    private AdData adData;
    private int id;

    public ProductRequestModel(int productId) {
        this.id = productId;
        this.adData = new AdData();
    }
}