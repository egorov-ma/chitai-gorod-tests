package ru.chitaigorod.api.models.search;

import lombok.Data;

import java.util.List;

@Data
public class Products {
    private List<DataItem> data;
    private Meta meta;
}