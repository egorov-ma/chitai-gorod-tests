package ru.chitaigorod.api.models.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pagination {
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    private int count;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("current_page")
    private int currentPage;
}