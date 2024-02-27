package ru.chitaigorod.api.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.api.models.product.ProductRequestModel;
import ru.chitaigorod.api.models.product.error.ProductErrorResponseModel;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.api.data.EndpointsData.PRODUCT;
import static ru.chitaigorod.api.specs.Specifications.requestSpec;
import static ru.chitaigorod.api.specs.Specifications.responseSpec;

public class ProductApi {
    @Step("POST-запрос Добавить в корзину продкут {productId}")
    public void postAddItem(int productId, String accessToken) {
        ProductRequestModel product = new ProductRequestModel(productId);
        given(requestSpec())
                .header("authorization", accessToken)
                .body(product)
                .when()
                .post(PRODUCT)
                .then()
                .spec(responseSpec());
    }

    @Step("POST-запрос Добавить в корзину не корректный продкут {productId}")
    public ProductErrorResponseModel postAddErrItem(int productId, String accessToken) {
        ProductRequestModel product = new ProductRequestModel(productId);
        return given(requestSpec())
                .header("authorization", accessToken)
                .body(product)
                .when().post(PRODUCT)
                .then().spec(responseSpec())
                .statusCode(500)
                .extract().as(ProductErrorResponseModel.class);
    }
}