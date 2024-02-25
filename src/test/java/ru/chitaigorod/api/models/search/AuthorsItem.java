package ru.chitaigorod.api.models.search;

import lombok.Data;

@Data
public class AuthorsItem {
    private String firstName;
    private String lastName;
    private String middleName;
    private int id;
    private String url;
}