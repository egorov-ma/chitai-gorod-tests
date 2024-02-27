package ru.chitaigorod.models.cartshort;

import lombok.Data;

import java.util.List;

@Data
public class DataModel {
    private int quantity;
    private List<Integer> items;
}