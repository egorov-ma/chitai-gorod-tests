package ru.chitaigorod.steps.search;

import io.qameta.allure.Step;
import ru.chitaigorod.models.search.SearchResponseModel;
import ru.chitaigorod.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.data.EndpointsData.PRODUCT_SEARCH;
import static ru.chitaigorod.data.EndpointsData.SEARCH_PARAM;

public class SearchApi {
    @Step("GET-запрос Поиск по {item}")
    public SearchResponseModel getSearch(String item, String accessToken) {
        return given(Specifications.requestSpec())
                .queryParam(SEARCH_PARAM, item)
                .header("authorization", accessToken)
                .when()
                .get(PRODUCT_SEARCH)
                .then()
                .spec(Specifications.responseSpec200())
                .extract().as(SearchResponseModel.class);
    }

    @Step("GET-запрос Поиск без параметров")
    public SearchErrorResponseModel getSearch(String accessToken) {
        return given(Specifications.requestSpec())
                .header("authorization", accessToken)
                .when()
                .get(PRODUCT_SEARCH)
                .then()
                .spec(Specifications.responseSpec400())
                .extract().as(SearchErrorResponseModel.class);
    }
}