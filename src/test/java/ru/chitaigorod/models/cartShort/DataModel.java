package ru.chitaigorod.models.cartShort;

import lombok.Data;

import java.util.List;

@Data
public class DataModel {
    private int quantity;
    private List<Integer> items;
}