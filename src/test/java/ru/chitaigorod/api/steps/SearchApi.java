package ru.chitaigorod.api.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.api.models.search.SearchResponseModel;
import ru.chitaigorod.api.models.search.error.SearchErrorResponseModel;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.api.data.EndpointsData.PRODUCT_SEARCH;
import static ru.chitaigorod.api.data.EndpointsData.SEARCH_PARAM;
import static ru.chitaigorod.api.specs.Specifications.requestSpec;
import static ru.chitaigorod.api.specs.Specifications.responseSpec;

public class SearchApi {
    @Step("GET-запрос Поиск по {item}")
    public SearchResponseModel getSearch(String item, String accessToken) {
        return given(requestSpec())
                .queryParam(SEARCH_PARAM, item)
                .header("authorization", accessToken)
                .when()
                .get(PRODUCT_SEARCH)
                .then()
                .spec(responseSpec())
                .statusCode(200)
                .extract().as(SearchResponseModel.class);
    }

    @Step("GET-запрос Поиск без параметров")
    public SearchErrorResponseModel getSearch(String accessToken) {
        return given(requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(PRODUCT_SEARCH)
                .then()
                .spec(responseSpec())
                .statusCode(400)
                .extract().as(SearchErrorResponseModel.class);
    }
}