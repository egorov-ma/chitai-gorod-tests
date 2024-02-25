package ru.chitaigorod.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static ru.chitaigorod.api.helpers.CustomApiListener.withCustomTemplates;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .addHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                        "eyJleHAiOjE3MDkwMjkxNDgsImlhdCI6MTcwODg2MTE0OCwiaXNzIjoiL2FwaS92MS9hdXRoL2Fub255bW" +
                        "91cyIsInN1YiI6IjA1ZTAzNTIxZTM2OTZhODAyOWNmNWI4MWU1MWZlZTVjNDkwNThkYzAyZGYzYjQxZmQwNz" +
                        "ZjZTA4OGE2Yjk2OWIiLCJ0eXBlIjoxMH0.N-OcaGqos9OQCeJxlx81iq08ZjP1hGXkG52iWhoUZcg")
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .setContentType(ContentType.JSON)
                .addFilter(withCustomTemplates())
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
    }
}