package ru.chitaigorod.models.cart.error;

import lombok.Data;

import java.util.List;

@Data
public class CartErrorResponseModel {
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
    private List<Object> products;
}