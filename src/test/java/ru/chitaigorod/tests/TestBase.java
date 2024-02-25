package ru.chitaigorod.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.chitaigorod.config.ConfigReader;
import ru.chitaigorod.config.ProjectConfiguration;
import ru.chitaigorod.config.WebConfig;
import ru.chitaigorod.helpers.Attach;

public class TestBase {
    private static final WebConfig webConfig = ConfigReader.Instance.read();

    public static final String BASE_URI = "https://web-gate.chitai-gorod.ru";

    @BeforeAll
    static void beforeAll() {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = "/api/";
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo("https://selenoid.autotests.cloud/video/");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}