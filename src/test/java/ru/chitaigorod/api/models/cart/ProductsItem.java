package ru.chitaigorod.api.models.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductsItem {
    private boolean inSubscription;
    private int goodsId;
    private AdData adData;
    private List<Object> coauthors;
    private int fullPrice;
    private String title;
    private boolean disabledBonuses;
    @JsonProperty("isBook")
    private boolean isBook;
    private int price;
    @JsonProperty("isBookmarks")
    private boolean isBookmarks;
    private boolean preOrder;
    private int id;
    private int stock;
    @JsonProperty("nForM")
    private NForM nForM;
    private int cost;
    private int quantity;
    private int weight;
    private String picture;
    private String url;
    private int fullCost;
    private boolean sale;
    @JsonProperty("isMagic")
    private boolean isMagic;
    private String publisher;
    private Category category;
    private List<AuthorsItem> authors;
    private String status;
}