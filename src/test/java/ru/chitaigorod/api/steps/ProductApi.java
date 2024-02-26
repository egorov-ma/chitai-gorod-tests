package ru.chitaigorod.api.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.api.models.product.ProductRequestModel;
import ru.chitaigorod.api.models.product.error.ProductErrorResponseModel;
import ru.chitaigorod.api.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.api.data.EndpointsData.PRODUCT;

public class ProductApi {

    @Step("POST-запрос Добавить в корзину продкут {productId}")
    public void postAddItem(int productId) {
        ProductRequestModel product = new ProductRequestModel(productId);
        given(Specifications.requestSpec())
                .body(product)
                .when().post(PRODUCT)
                .then().spec(Specifications.responseSpec());
    }

    @Step("POST-запрос Добавить в корзину не корректный продкут {productId}")
    public ProductErrorResponseModel postAddErrItem(int productId) {
        ProductRequestModel product = new ProductRequestModel(productId);
        return given(Specifications.requestSpec())
                .body(product)
                .when().post(PRODUCT)
                .then().spec(Specifications.responseSpec())
                .statusCode(500)
                .extract().as(ProductErrorResponseModel.class);
    }
}