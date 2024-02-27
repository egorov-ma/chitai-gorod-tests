package ru.chitaigorod.steps.cart;

import io.qameta.allure.Step;
import ru.chitaigorod.models.deleteCart.DeleteCartResponseModel;
import ru.chitaigorod.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.data.EndpointsData.*;

public class DeleteCartApi {
    @Step("DELETE-запрос Очистить всю корзину")
    public void allCart(String accessToken) {
        given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .delete(CART);
    }

    @Step("DELETE-запрос Очистить продукт в корзине")
    public void productItem(int item, String accessToken) {
        given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .delete(DELETE_ITEM + item);
    }

    @Step("DELETE-запрос Очистить в корзине продкут {item}")
    public DeleteCartResponseModel err(int item, String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .delete(DELETE_ITEM + item)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(404)
                .extract().as(DeleteCartResponseModel.class);
    }
}
