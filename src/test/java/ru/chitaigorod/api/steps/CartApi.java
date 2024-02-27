package ru.chitaigorod.api.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.api.models.cart.CartResponseModel;
import ru.chitaigorod.api.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.api.models.cartshort.CartShortResponseModel;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.api.data.EndpointsData.CART;
import static ru.chitaigorod.api.data.EndpointsData.CART_SHORT;
import static ru.chitaigorod.api.specs.Specifications.*;

public class CartApi {
    @Step("DELETE-запрос Очистить корзину")
    public void deleteCart(String accessToken) {
        given(requestSpec())
                .header("authorization", accessToken)
                .when()
                .delete(CART);
    }

    @Step("GET-запрос Карзина кратко")
    public CartShortResponseModel getCartShort(String accessToken) {
        return given(requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(CART_SHORT)
                .then()
                .spec(responseSpec())
                .statusCode(200)
                .extract().as(CartShortResponseModel.class);
    }

    @Step("GET-запрос Карзина")
    public CartResponseModel getCart(String accessToken) {
        return given(requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(CART)
                .then()
                .spec(responseSpec())
                .statusCode(200)
                .extract().as(CartResponseModel.class);
    }

    @Step("GET-запрос Карзина пустая")
    public CartErrorResponseModel getErrCart(String accessToken) {
        return given(requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(CART)
                .then()
                .spec(responseSpec())
                .statusCode(200)
                .extract().as(CartErrorResponseModel.class);
    }
}