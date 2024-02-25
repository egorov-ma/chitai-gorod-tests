package ru.chitaigorod.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement linkHeaderCart = $("a[class*='sticky-header']");


    @Step("Открываем главную страницу - {url}")
    public void openMainPage(String url) {
        open(url);
//        executeJavaScript("$('#fixedban').remove()");
//        executeJavaScript("$('footer').remove()");
    }

    @Step("Нажимаем кнопку 'Вход'")
    public void clickCart() {
        linkHeaderCart.click();
    }
}
