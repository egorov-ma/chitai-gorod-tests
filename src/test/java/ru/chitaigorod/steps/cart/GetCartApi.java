package ru.chitaigorod.steps.cart;

import io.qameta.allure.Step;
import ru.chitaigorod.models.cart.CartResponseModel;
import ru.chitaigorod.models.cart.error.CartErrorResponseModel;
import ru.chitaigorod.models.cartshort.CartShortResponseModel;
import ru.chitaigorod.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.data.EndpointsData.CART;
import static ru.chitaigorod.data.EndpointsData.CART_SHORT;

public class GetCartApi {
    @Step("GET-запрос Карзина кратко")
    public CartShortResponseModel getCartShort(String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(CART_SHORT)
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
                .get(CART)
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
                .get(CART)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(CartErrorResponseModel.class);
    }
}