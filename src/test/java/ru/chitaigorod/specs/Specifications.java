package ru.chitaigorod.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.chitaigorod.helpers.CustomApiListener;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .setContentType(ContentType.JSON)
                .addFilter(CustomApiListener.withCustomTemplates())
                .build();
    }

    public static ResponseSpecification responseSpec200() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpec400() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpec404() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .expectStatusCode(404)
                .build();
    }

    public static ResponseSpecification responseSpec500() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .expectStatusCode(500)
                .build();
    }
}