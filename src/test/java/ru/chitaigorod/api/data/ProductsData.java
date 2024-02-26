package ru.chitaigorod.api.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class ProductsData {

    private final Faker faker = new Faker(new Locale("ru"));
    public final String productItem = "чистый код",
    randomProductItem = faker.book().author().toLowerCase();
    public final int goodsId = 2640391,
            errorId = 1,
            productId = 112163558;
}