package ru.chitaigorod.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import ru.chitaigorod.pages.MainPage;

import java.util.Set;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.lang.Thread.sleep;

@DisplayName("Проверка chitai-gorod.ru")
@Tag("UI")
public class ChitaiGorodUiTests extends TestBase {

    MainPage mainPage = new MainPage();

    @Test
    @Feature("Тесты на главной")
    @Story("Позитивный тест")
    @Owner("@egorovma")
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://www.chitai-gorod.ru/")
    @DisplayName("Проверка открытия главной")
    void mainPageTest() throws InterruptedException {
        mainPage.openMainPage(baseUrl);
        mainPage.clickCart();
        Set<Cookie> cookies = getWebDriver().manage().getCookies();
        String acc = getWebDriver().manage().getCookieNamed("access-token").getValue();
        System.out.println(cookies);
        System.out.println(acc);
        sleep(5000);

//        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));

//        mainPage.checkMainHeader(); Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDg3NjU1NzYsImlhdCI6MTcwODU5NzU3NiwiaXNzIjoiL2FwaS92MS9hdXRoL2Fub255bW91cyIsInN1YiI6ImI0OWM5N2E0NTJkYzg0N2I4N2ZmY2EyMzNkODc4YTJhZTQ4MDc3Yzc3ZWUxNzA1YWMxOTI1NDY3ZTJhOThiZjAiLCJ0eXBlIjoxMH0.bcEc8FzDj0J6LQGaIFHUlRGWHH_KSllHo6QFnFB8mTk
    }
}
