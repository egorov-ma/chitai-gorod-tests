package ru.chitaigorod.api.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.api.data.ProductsData;
import ru.chitaigorod.api.models.cart.CartResponseModel;
import ru.chitaigorod.api.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.api.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.api.models.product.error.ProductErrorResponseModel;
import ru.chitaigorod.api.models.search.SearchResponseModel;
import ru.chitaigorod.api.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.api.steps.CartApi;
import ru.chitaigorod.api.steps.ProductApi;
import ru.chitaigorod.api.steps.SearchApi;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("API")
public class ChitaiGorodTests extends TestBase {

    CartApi cart = new CartApi();
    ProductApi product = new ProductApi();
    SearchApi search = new SearchApi();
    ProductsData data = new ProductsData();

    @Test
    @Story("Поиск продукта")
    @DisplayName("Успешный Поиск продукта")
    void searchProductTest() {
        SearchResponseModel response = step("Ищем продукт", () -> search.getSearch(data.productItem));
        step("Проверка ответа, соответствия transformedPhrase", () ->
                assertThat(response.getData().getAttributes().getTransformedPhrase()).isEqualTo(data.productItem));
    }

    @Test
    @Story("Поиск продукта")
    @DisplayName("Не успешный Поиск продукта")
    void negativeSearchProductTest() {
        SearchErrorResponseModel response = step("Ищем продукт", () -> search.getSearch());
        step("Проверка ответа, соответствия status", () ->
                assertThat(response.getErrors().get(0).getStatus()).isEqualTo("400"));
        step("Проверка ответа, соответствия title", () ->
                assertThat(response.getErrors().get(0).getTitle()).isEqualTo("Phrase обязательное поле"));
    }

    @Test
    @Story("Добавление в Корзину")
    @DisplayName("Успешное Добавление в корзину")
    void cartShortTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart());

        step("Добавляем продкут", () -> product.postAddItem(data.goodsId));

        CartShortResponseModel response = step("Проверяем корзину", () -> cart.getCartShort());
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(response.getData().getItems().get(0)).isEqualTo(data.goodsId));
        step("Проверка ответа, количество продуктов в корзине", () ->
                assertThat(response.getData().getQuantity()).isEqualTo(1));
    }

    @Test
    @Story("Добавление в Корзину")
    @DisplayName("Не успешное Добавление в корзину")
    void negativeCartShortTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart());

        ProductErrorResponseModel responseErr = step("Добавляем продкут", () -> product.postAddErrItem(data.errorId));
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(responseErr.getMessage()).isEqualTo("данного товара не существует"));

        CartShortResponseModel response = step("Проверяем корзину", () -> cart.getCartShort());
        step("Проверка ответа, количество продуктов в корзине", () ->
                assertThat(response.getData().getQuantity()).isEqualTo(0));
    }

    @Test
    @Story("Подробный состав корзины")
    @DisplayName("Успешный просмотр состава корзины")
    void cartTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart());
        step("Подготавливаем, добавляем продкут", () -> product.postAddItem(data.goodsId));

        CartResponseModel response = step("Проверяем корзину", () -> cart.getCart());
        step("Проверка ответа, соответствия goodsId", () ->
                assertThat(response.getProducts().get(0).getGoodsId()).isEqualTo(data.goodsId));
        step("Проверка ответа, соответствия id", () ->
                assertThat(response.getProducts().get(0).getId()).isEqualTo(data.productId));
    }

    @Test
    @Story("Подробный состав корзины")
    @DisplayName("Не успешный просмотр состава корзины")
    void negativeCartTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart());

        CartErrorResponseModel response = step("Проверяем корзину", () -> cart.getErrCart());
        step("Проверка ответа, соответствия cost", () ->
                assertThat(response.getCost()).isEqualTo(0));
    }
}