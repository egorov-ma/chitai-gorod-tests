package ru.chitaigorod.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.models.cart.CartResponseModel;
import ru.chitaigorod.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.data.EndpointsData;
import ru.chitaigorod.specs.Specifications;

import static io.restassured.RestAssured.given;

public class CartApi {
    @Step("DELETE-запрос Очистить корзину")
    public void deleteCart(String accessToken) {
        given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .delete(EndpointsData.CART);
    }

    @Step("GET-запрос Карзина кратко")
    public CartShortResponseModel getCartShort(String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(EndpointsData.CART_SHORT)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartShortResponseModel.class);
    }

    @Step("GET-запрос Карзина")
    public CartResponseModel getCart(String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(EndpointsData.CART)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartResponseModel.class);
    }

    @Step("GET-запрос Карзина пустая")
    public CartErrorResponseModel getErrCart(String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(EndpointsData.CART)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartErrorResponseModel.class);
    }
}