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
import ru.chitaigorod.steps.CartApi;
import ru.chitaigorod.steps.ProductApi;
import ru.chitaigorod.steps.SearchApi;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("add")})
@Story("Добавление в Корзину")
@DisplayName("Добавление в Корзину")
public class AddProductApiTest extends TestBase {
    String token = getAccessToken();
    ProductsData data = new ProductsData();
    SearchApi search = new SearchApi();
    CartApi cart = new CartApi();
    ProductApi product = new ProductApi();

    @Test
    @DisplayName("Успешное Добавление в корзину")
    void addProductTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));
        SearchResponseModel searchProduct = step("Ищем продукт", () -> search.getSearch(data.author, token));
        step("Подготавливаем, добавляем продкут", () ->
                product.postAddItem(searchProduct.getIncluded().get(0).getAttributes().getId(), token));
        CartShortResponseModel response = step("Проверяем корзину", () -> cart.getCartShort(token));
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(response.getData().getItems().get(0))
                        .isEqualTo(searchProduct.getIncluded().get(0).getAttributes().getId()));
    }

    @Test
    @DisplayName("Не успешное Добавление в корзину")
    void negativeAddProductTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));

        ProductErrorResponseModel responseErr = step("Добавляем продкут", () -> product.postAddErrItem(data.errorId, token));
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(responseErr.getMessage()).isEqualTo("данного товара не существует"));

        CartShortResponseModel response = step("Проверяем корзину", () -> cart.getCartShort(token));
        step("Проверка ответа, количество продуктов в корзине", () ->
                assertThat(response.getData().getQuantity()).isEqualTo(0));
    }
}