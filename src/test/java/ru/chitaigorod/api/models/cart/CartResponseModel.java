package ru.chitaigorod.api.models.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseModel {
    private int addBonuses;
    private int cost;
    private int costWithBonuses;
    private List<Object> disabledProducts;
    private int discount;
    private int weight;
    private Object promoCode;
    private int costWithSale;
    private List<Object> preorderProducts;
    private Object costGiftWrap;
    private List<Object> gifts;
    private List<ProductsItem> products;
}