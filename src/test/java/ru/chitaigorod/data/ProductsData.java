package ru.chitaigorod.data;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class ProductsData {

    private final Faker faker = new Faker(new Locale("ru"));
    public final String randomProductItem = faker.book().author().toLowerCase();
    public final int goodsId = 2640391,
            errorId = 1,
            productId = 112431485;

    public static String getAccessToken() {
        Map<String, String> allCookies = get("https://www.chitai-gorod.ru/").getCookies();
        String accessToken = allCookies.get("access-token");
        accessToken = accessToken.replace("%20", " ");
        return accessToken;
    }
}