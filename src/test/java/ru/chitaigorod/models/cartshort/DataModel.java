package ru.chitaigorod.models.cartshort;

import java.util.List;

import lombok.Data;

@Data
public class DataModel {
    private int quantity;
    private List<Integer> items;
}