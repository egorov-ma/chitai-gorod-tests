package ru.chitaigorod.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.chitaigorod.data.ProductsData;
import ru.chitaigorod.models.cart.CartResponseModel;
import ru.chitaigorod.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.models.deleteCart.DeleteCartResponseModel;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.steps.cart.CartSteps;
import ru.chitaigorod.steps.product.ProductSteps;
import ru.chitaigorod.steps.search.SearchSteps;

import static io.qameta.allure.Allure.step;
import static ru.chitaigorod.helpers.AuthToken.getAccessToken;

@Tags({@Tag("api"), @Tag("delete")})
@Story("Очистка корзины")
@DisplayName("Очистка корзины")
public class DeleteCartApiTests extends TestBase {
    String token = getAccessToken();
    ProductsData data = new ProductsData();
    CartSteps cartSteps = new CartSteps();
    SearchSteps searchSteps = new SearchSteps();
    ProductSteps productSteps = new ProductSteps();

    @Test
    @DisplayName("Удаление продкута из корзины")
    void deleteProductTest() {
        step("Подготавливаем, очищаем корзину", () -> cartSteps.deleteAllCart(token));
        SearchResponseModel searchProduct = step("Ищем продукт", () -> searchSteps.searchItem(data.author, token));
        step("Подготавливаем, добавляем продкут", () -> productSteps.addItemById(searchProduct, token));
        CartResponseModel cart = step("Подготавливаем, проверяем корзину", () -> cartSteps.getCart(token));
        step("Проверка ответа, соответствия Id", () -> cartSteps.checkCartProductId(cart, searchProduct));

        step("Удаляем продукт", () -> cartSteps.deleteItemById(cart, token));
        CartShortResponseModel cartShort = step("Проверяем корзину", () -> cartSteps.getCartShort(token));

        step("Проверка ответа, корзина пустая", () -> cartSteps.checkCartShortNull(cartShort));
    }

    @Test
    @DisplayName("Ошибка при удалении продкута из корзины")
    void negativeDeleteProductTest() {
        step("Подготавливаем, очищаем корзину", () -> cartSteps.deleteAllCart(token));
        SearchResponseModel searchProduct = step("Ищем продукт", () -> searchSteps.searchItem(data.author, token));
        step("Подготавливаем, добавляем продкут", () -> productSteps.addItemById(searchProduct, token));
        CartResponseModel cart = step("Подготавливаем, проверяем корзину", () -> cartSteps.getCart(token));
        step("Проверка ответа, соответствия Id", () -> cartSteps.checkCartProductId(cart, searchProduct));

        step("Удаляем продукт", () -> cartSteps.deleteItemById(cart, token));
        DeleteCartResponseModel response = step("Повторно удаляем продукт", () ->
                cartSteps.deleteAgainItemByIde(cart, token));

        step("Проверка ответа, ошибка", () -> cartSteps.checkDeleteErrMsg(response));
    }
}