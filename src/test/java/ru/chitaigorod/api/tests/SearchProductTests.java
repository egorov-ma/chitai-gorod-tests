package ru.chitaigorod.api.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.api.data.ProductsData;
import ru.chitaigorod.api.models.search.SearchResponseModel;
import ru.chitaigorod.api.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.api.steps.SearchApi;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tags({@Tag("api"), @Tag("search")})
@Story("Поиск продукта")
public class SearchProductTests extends TestBase{
    ProductsData data = new ProductsData();
    SearchApi search = new SearchApi();

    @Test
    @DisplayName("Успешный Поиск продукта")
    void searchProductTest() {
        SearchResponseModel response = step("Ищем продукт", () -> search.getSearch(data.randomProductItem));
        step("Проверка ответа, соответствия transformedPhrase", () ->
                assertThat(response.getData().getAttributes().getTransformedPhrase()).isEqualTo(data.randomProductItem));
    }

    @Test
    @DisplayName("Не успешный Поиск продукта")
    void negativeSearchProductTest() {
        SearchErrorResponseModel response = step("Ищем продукт", () -> search.getSearch());
        step("Проверка ответа, соответствия status", () ->
                assertThat(response.getErrors().get(0).getStatus()).isEqualTo("400"));
        step("Проверка ответа, соответствия title", () ->
                assertThat(response.getErrors().get(0).getTitle()).isEqualTo("Phrase обязательное поле"));
    }
}