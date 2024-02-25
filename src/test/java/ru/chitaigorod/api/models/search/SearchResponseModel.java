package ru.chitaigorod.api.models.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponseModel {
    private SearchData data;
    private List<IncludedItem> included;
}