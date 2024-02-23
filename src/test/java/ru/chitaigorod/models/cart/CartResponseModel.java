package ru.chitaigorod.models.cart;

import java.util.List;
import lombok.Data;

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