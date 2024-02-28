package ru.chitaigorod.helpers;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class AuthToken {
    public final static String TEMP_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
            ".eyJleHAiOjE3MDkyMjUwMTQsImlhdCI6MTcwOTA1NzAxNCwiaXNzIjoiL2FwaS92MS9hdX" +
            "RoL2Fub255bW91cyIsInN1YiI6ImI1ZDM3ZDM1ZTg5NjQyYzIxOGYyZDYwYjU4NjU2YmI4M" +
            "2JiMjZhZGRlOGRmYjRiODRlNTEyOTA0YjZkOTRkMGEiLCJ0eXBlIjoxMH0" +
            ".vhVqgbYfleN9fOYXsgwQETcy09I6r0laSijT6rSyLCA";

    public static String getAccessToken() {
        // todo лютый костыль но локально работает а в jenkins не возвращается токен. надо будет поправть if()
        Map<String, String> allCookies = get("https://www.chitai-gorod.ru/").getCookies();

        System.out.println("###################################################");
        allCookies.forEach((key, value) -> System.out.println(key + ":" + value));

        String accessToken = allCookies.get("access-token");

        if (accessToken != null) {
            accessToken = accessToken.replace("%20", " ");
            return accessToken;
        } else {
            return TEMP_TOKEN;
        }
    }
}