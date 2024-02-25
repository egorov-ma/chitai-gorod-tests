package ru.chitaigorod.api.models.search;

import lombok.Data;

@Data
public class Rating {
    private String count;
    private int votes;
}