package ru.chitaigorod.models.search;

import lombok.Data;

@Data
public class SearchData {
    private Relationships relationships;
    private Attributes attributes;
    private String id;
    private String type;
}