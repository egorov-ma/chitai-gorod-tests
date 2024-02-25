package ru.chitaigorod.api.models.search.error;

import lombok.Data;

@Data
public class ErrorsItem {
    private String code;
    private Source source;
    private String title;
    private String status;
}