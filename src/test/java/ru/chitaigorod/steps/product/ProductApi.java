package ru.chitaigorod.steps.product;

import io.qameta.allure.Step;
import ru.chitaigorod.models.product.ProductRequestModel;
import ru.chitaigorod.models.product.error.ProductErrorResponseModel;
import ru.chitaigorod.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.data.EndpointsData.PRODUCT;

public class ProductApi {
    @Step("POST-запрос Добавить в корзину продкут {productId}")
    public void postAddItem(int productId, String accessToken) {
        ProductRequestModel product = new ProductRequestModel(productId);
        given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .body(product)
                .when()
                .post(PRODUCT)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200);
    }

    @Step("POST-запрос Добавить в корзину не корректный продкут {productId}")
    public ProductErrorResponseModel postAddErrItem(int productId, String accessToken) {
        ProductRequestModel product = new ProductRequestModel(productId);
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .body(product)
                .when().post(PRODUCT)
                .then().spec(Specifications.responseSpec())
                .statusCode(500)
                .extract().as(ProductErrorResponseModel.class);
    }
}