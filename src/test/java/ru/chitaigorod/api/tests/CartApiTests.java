package ru.chitaigorod.api.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.api.data.ProductsData;
import ru.chitaigorod.api.models.cart.CartResponseModel;
import ru.chitaigorod.api.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.api.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.api.models.product.error.ProductErrorResponseModel;
import ru.chitaigorod.api.steps.CartApi;
import ru.chitaigorod.api.steps.ProductApi;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.api.data.ProductsData.getAccessToken;

@DisplayName("API")
public class CartApiTests extends TestBase {

    CartApi cart = new CartApi();
    ProductApi product = new ProductApi();
    ProductsData data = new ProductsData();
    String token = getAccessToken();

    @Test
    @Story("Добавление в Корзину")
    @DisplayName("Успешное Добавление в корзину")
    void cartShortTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));

        step("Добавляем продкут", () -> product.postAddItem(data.goodsId, token));

        CartShortResponseModel response = step("Проверяем корзину", () -> cart.getCartShort(token));
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(response.getData().getItems().get(0)).isEqualTo(data.goodsId));
        step("Проверка ответа, количество продуктов в корзине", () ->
                assertThat(response.getData().getQuantity()).isEqualTo(1));
    }

    @Test
    @Story("Добавление в Корзину")
    @DisplayName("Не успешное Добавление в корзину")
    void negativeCartShortTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));

        ProductErrorResponseModel responseErr = step("Добавляем продкут", () -> product.postAddErrItem(data.errorId, token));
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(responseErr.getMessage()).isEqualTo("данного товара не существует"));

        CartShortResponseModel response = step("Проверяем корзину", () -> cart.getCartShort(token));
        step("Проверка ответа, количество продуктов в корзине", () ->
                assertThat(response.getData().getQuantity()).isEqualTo(0));
    }

    @Test
    @Story("Подробный состав корзины")
    @DisplayName("Успешный просмотр состава корзины")
    void cartTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));
        step("Подготавливаем, добавляем продкут", () -> product.postAddItem(data.goodsId, token));

        CartResponseModel response = step("Проверяем корзину", () -> cart.getCart(token));
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(response.getProducts().get(0).getGoodsId()).isEqualTo(data.goodsId));
        step("Проверка ответа, соответствия id", () ->
                assertThat(response.getProducts().get(0).getId()).isEqualTo(data.productId));
    }

    @Test
    @Story("Подробный состав корзины")
    @DisplayName("Не успешный просмотр состава корзины")
    void negativeCartTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));

        CartErrorResponseModel response = step("Проверяем корзину", () -> cart.getErrCart(token));
        step("Проверка ответа, соответствия cost", () ->
                assertThat(response.getCost()).isEqualTo(0));
    }
}