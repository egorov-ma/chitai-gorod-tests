package ru.chitaigorod.api.models.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Attributes {
    private String strategy;
    private String transformedPhrase;
    private int count;
    private String originalPicture;
    private String code;
    private boolean inSubscription;
    private boolean saleSoon;
    private Rating rating;
    private String binding;
    private String description;
    private String discount;
    private List<Object> coauthors;
    private String title;
    @JsonProperty("isBook")
    private boolean isBook;
    private String pages;
    private int price;
    private boolean exclusive;
    @JsonProperty("isBookmarks")
    private boolean isBookmarks;
    private int id;
    private Object startSale;
    private String startSaleDesc;
    @JsonProperty("new")
    private boolean jsonMemberNew;
    private int oldPrice;
    private String picture;
    private String preOrderDate;
    private String url;
    private boolean recommended;
    private boolean sale;
    private boolean bestseller;
    private Publisher publisher;
    private Category category;
    private int yearPublishing;
    private List<AuthorsItem> authors;
    private String status;
}