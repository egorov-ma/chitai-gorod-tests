package ru.chitaigorod.api.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.api.models.cart.CartResponseModel;
import ru.chitaigorod.api.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.api.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.api.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.api.data.EndpointsData.CART;
import static ru.chitaigorod.api.data.EndpointsData.CART_SHORT;

public class CartApi {


    @Step("DELETE-запрос Очистить корзину")
    public void deleteCart() {
        given(Specifications.requestSpec())
                .when()
                .delete(CART);
    }

    @Step("GET-запрос Карзина кратко")
    public CartShortResponseModel getCartShort() {
        return given(Specifications.requestSpec())
                .when()
                .get(CART_SHORT)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartShortResponseModel.class);
    }

    @Step("GET-запрос Карзина")
    public CartResponseModel getCart() {
        return given(Specifications.requestSpec())
                .when()
                .get(CART)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartResponseModel.class);
    }

    @Step("GET-запрос Карзина пустая")
    public CartErrorResponseModel getErrCart() {
        return given(Specifications.requestSpec())
                .when()
                .get(CART)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartErrorResponseModel.class);
    }
}