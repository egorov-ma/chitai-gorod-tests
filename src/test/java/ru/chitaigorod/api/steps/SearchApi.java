package ru.chitaigorod.api.steps;

import io.qameta.allure.Step;
import ru.chitaigorod.api.models.search.SearchResponseModel;
import ru.chitaigorod.api.models.search.error.SearchErrorResponseModel;
import ru.chitaigorod.api.specs.Specifications;

import static io.restassured.RestAssured.given;
import static ru.chitaigorod.api.data.EndpointsData.PRODURT_SEARCH;

public class SearchApi {
    @Step("GET-запрос Поиск")
    public SearchResponseModel getSearch(String item) {
        return given(Specifications.requestSpec())
                .when()
                .get(PRODURT_SEARCH + item)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(200)
                .extract().as(SearchResponseModel.class);
    }

    @Step("GET-запрос Поиск без параметров")
    public SearchErrorResponseModel getSearch() {
        return given(Specifications.requestSpec())
                .when()
                .get(PRODURT_SEARCH)
                .then()
                .spec(Specifications.responseSpec())
                .statusCode(400)
                .extract().as(SearchErrorResponseModel.class);
    }
}