package ru.chitaigorod.api.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://web-gate.chitai-gorod.ru";
        RestAssured.basePath = "/api";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}