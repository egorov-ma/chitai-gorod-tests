package ru.chitaigorod.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.data.ProductsData;
import ru.chitaigorod.models.cart.CartResponseModel;
import ru.chitaigorod.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.steps.cart.CartSteps;
import ru.chitaigorod.steps.product.ProductSteps;
import ru.chitaigorod.steps.search.SearchSteps;

import static io.qameta.allure.Allure.step;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("cart")})
@Story("Проверка корзины")
@DisplayName("Проверка корзины")
public class GetCartApiTests extends TestBase {
    String token = getAccessToken();
    ProductsData data = new ProductsData();
    SearchSteps searchSteps = new SearchSteps();
    ProductSteps productSteps = new ProductSteps();
    CartSteps cartSteps = new CartSteps();

    @Test
    @DisplayName("Успешная проверка состава корзины")
    void cartTest() {
        step("Подготавливаем, очищаем корзину", () -> cartSteps.deleteAllCart(token));
        SearchResponseModel searchProduct = step("Ищем продукт", () -> searchSteps.searchItem(data.author, token));
        step("Подготавливаем, добавляем продкут", () -> productSteps.addItemById(searchProduct, token));

        CartResponseModel response = step("Проверяем корзину", () -> cartSteps.getCart(token));

        step("Проверка ответа, соответствия id продукта из поиска и id в корзине", () ->
                cartSteps.checkCartProductId(response,searchProduct));
    }

    @Test
    @DisplayName("Не успешный просмотр состава корзины")
    void negativeCartTest() {
        step("Подготавливаем, очищаем корзину", () -> cartSteps.deleteAllCart(token));

        CartErrorResponseModel response = step("Проверяем корзину", () -> cartSteps.getCartErr(token));

        step("Проверка ответа, соответствия cost", () -> cartSteps.checkCartCostNull(response));
    }
}