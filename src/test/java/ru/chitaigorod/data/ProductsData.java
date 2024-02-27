package ru.chitaigorod.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class ProductsData {
    private final Faker faker = new Faker(new Locale("ru"));
    public final String author = faker.book().author().toLowerCase();
    public final int errorId = Integer.parseInt(faker.number().digits(8));
}