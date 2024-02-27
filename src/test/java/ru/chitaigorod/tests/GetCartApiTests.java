package ru.chitaigorod.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.data.ProductsData;
import ru.chitaigorod.models.cart.CartResponseModel;
import ru.chitaigorod.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.steps.CartApi;
import ru.chitaigorod.steps.ProductApi;
import ru.chitaigorod.steps.SearchApi;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("cart")})
@Story("Проверка корзины")
@DisplayName("Проверка корзины")
public class GetCartApiTests extends TestBase {
    String token = getAccessToken();
    ProductsData data = new ProductsData();
    CartApi cart = new CartApi();
    ProductApi product = new ProductApi();
    SearchApi search = new SearchApi();

    @Test
    @DisplayName("Успешная проверка состава корзины")
    void cartTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));
        SearchResponseModel searchProduct = step("Ищем продукт", () -> search.getSearch(data.author, token));
        step("Подготавливаем, добавляем продкут", () ->
                product.postAddItem(searchProduct.getIncluded().get(0).getAttributes().getId(), token));

        CartResponseModel response = step("Проверяем корзину", () -> cart.getCart(token));
        Allure.step("Проверка ответа, соответствия id продукта из поиска и id в корзине", () ->
                assertThat(response.getProducts().get(0).getGoodsId())
                        .isEqualTo(searchProduct.getIncluded().get(0).getAttributes().getId()));
    }

    @Test
    @DisplayName("Не успешный просмотр состава корзины")
    void negativeCartTest() {
        step("Подготавливаем, очищаем корзину", () -> cart.deleteCart(token));
        CartErrorResponseModel response = step("Проверяем корзину", () -> cart.getErrCart(token));
        step("Проверка ответа, соответствия cost", () ->
                assertThat(response.getCost()).isEqualTo(0));
    }
}