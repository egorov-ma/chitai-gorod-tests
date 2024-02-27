package ru.chitaigorod.helpers;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class AuthToken {
    public static String getAccessToken() {
        Map<String, String> allCookies = get("https://www.chitai-gorod.ru/").getCookies();
        System.out.println("###########################");
        System.out.println(allCookies);
        System.out.println("###########################");
        allCookies.forEach((key, value) -> System.out.println(key + ":" + value));
        String accessToken = allCookies.get("access-token");
        accessToken = accessToken.replace("%20", " ");
        return accessToken;
    }
}
