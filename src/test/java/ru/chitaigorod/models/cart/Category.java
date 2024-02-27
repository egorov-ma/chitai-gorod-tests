package ru.chitaigorod.models.cart;

import lombok.Data;

@Data
public class Category {
    private int id;
    private String title;
    private String slug;
    private String url;
}