package ru.chitaigorod.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.data.ProductsData;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.steps.search.SearchSteps;

import static io.qameta.allure.Allure.step;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("search")})
@Story("Поиск продукта")
@DisplayName("Поиск продукта")
public class SearchProductApiTests extends TestBase {
    String token = getAccessToken();
    ProductsData data = new ProductsData();
    SearchSteps searchSteps = new SearchSteps();

    @Test
    @DisplayName("Успешный Поиск продукта")
    void searchProductTest() {
        SearchResponseModel response = step("Ищем продукт", () -> searchSteps.searchItem(data.author, token));

        step("Проверка ответа, соответствия transformedPhrase", () ->
                searchSteps.checkSearchTransformedPhrase(response, data.author));
    }

    @Test
    @DisplayName("Поиск без параметра")
    void negativeSearchProductTest1() {
        SearchErrorResponseModel response = step("Ищем продукт", () -> searchSteps.searchItem(token));
        step("Проверка ответа, соответствия title", () -> searchSteps.checkSearchErrTitle(response));
    }
}