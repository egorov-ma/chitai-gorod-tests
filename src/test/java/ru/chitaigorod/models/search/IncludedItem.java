package ru.chitaigorod.models.search;

import lombok.Data;

@Data
public class IncludedItem {
    private Attributes attributes;
    private String id;
    private String type;
}