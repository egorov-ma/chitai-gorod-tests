package ru.chitaigorod.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.data.ProductsData;
import ru.chitaigorod.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.models.product.error.ProductErrorResponseModel;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.steps.cart.CartSteps;
import ru.chitaigorod.steps.product.ProductSteps;
import ru.chitaigorod.steps.search.SearchSteps;

import static io.qameta.allure.Allure.step;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("add")})
@Story("Добавление в Корзину")
@DisplayName("Добавление в Корзину")
public class AddProductApiTest extends TestBase {
    String token = getAccessToken();
    ProductsData data = new ProductsData();
    SearchSteps searchSteps = new SearchSteps();
    ProductSteps productSteps = new ProductSteps();
    CartSteps cartSteps = new CartSteps();

    @Test
    @DisplayName("Успешное Добавление в корзину")
    void addProductTest() {
        step("Подготавливаем, очищаем корзину", () -> cartSteps.deleteAllCart(token));
        SearchResponseModel searchProduct = step("Ищем продукт", () -> searchSteps.searchItem(data.author, token));

        step("Добавляем продкут", () -> productSteps.addItemById(searchProduct, token));

        CartShortResponseModel response = step("Проверяем корзину", () -> cartSteps.getCartShort(token));
        step("Проверка ответа, соответствия Id", () -> cartSteps.checkCartShortProductId(response, searchProduct));
    }

    @Test
    @DisplayName("Не успешное Добавление в корзину")
    void negativeAddProductTest() {
        step("Подготавливаем, очищаем корзину", () -> cartSteps.deleteAllCart(token));

        ProductErrorResponseModel product = step("Добавляем продкут", () ->
                productSteps.addErrItemById(data.errorId, token));

        step("Проверка ответа, соответствия Id", () -> productSteps.checkErrMsg(product));
        CartShortResponseModel response = step("Проверяем корзину", () -> cartSteps.getCartShort(token));
        step("Проверка ответа, количество продуктов в корзине", () -> cartSteps.checkCartShortEmpty(response));
    }
}