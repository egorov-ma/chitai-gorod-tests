package ru.chitaigorod.steps.search;

import io.qameta.allure.Step;
import ru.chitaigorod.data.EndpointsData;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.specs.Specifications;

import static io.restassured.RestAssured.given;

public class SearchApi {
    @Step("GET-запрос Поиск по {item}")
    public SearchResponseModel getSearch(String item, String accessToken) {
        return given(Specifications.requestSpec())
                .queryParam(EndpointsData.SEARCH_PARAM, item)
                .header("authorization", accessToken)
                .when()
                .get(EndpointsData.PRODUCT_SEARCH)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(SearchResponseModel.class);
    }

    @Step("GET-запрос Поиск без параметров")
    public SearchErrorResponseModel getSearch(String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(EndpointsData.PRODUCT_SEARCH)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(400)
                .extract().as(SearchErrorResponseModel.class);
    }
}