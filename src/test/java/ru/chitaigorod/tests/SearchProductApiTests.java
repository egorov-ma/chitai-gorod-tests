package ru.chitaigorod.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.data.ProductsData;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.steps.search.SearchApi;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("search")})
@Story("Поиск продукта")
@DisplayName("Поиск продукта")
public class SearchProductApiTests extends TestBase {
    ProductsData data = new ProductsData();
    SearchApi search = new SearchApi();
    String token = getAccessToken();

    @Test
    @DisplayName("Успешный Поиск продукта")
    void searchProductTest() {
        SearchResponseModel response = step("Ищем продукт", () -> search.getSearch(data.author, token));
        step("Проверка ответа, соответствия transformedPhrase", () ->
                assertThat(response.getData().getAttributes().getTransformedPhrase()).isEqualTo(data.author));
    }

    @Test
    @DisplayName("Поиск без параметра")
    void negativeSearchProductTest1() {
        SearchErrorResponseModel response = step("Ищем продукт", () -> search.getSearch(token));
        step("Проверка ответа, соответствия status", () ->
                assertThat(response.getErrors().get(0).getStatus()).isEqualTo("400"));
        step("Проверка ответа, соответствия title", () ->
                assertThat(response.getErrors().get(0).getTitle()).isEqualTo("Phrase обязательное поле"));
    }
}