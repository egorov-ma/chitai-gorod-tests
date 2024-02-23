package ru.chitaigorod.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static ru.chitaigorod.helpers.CustomApiListener.withCustomTemplates;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .addHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDg3NjU1NzYsImlhdCI6MTcwODU5NzU3NiwiaXNzIjoiL2FwaS92MS9hdXRoL2Fub255bW91cyIsInN1YiI6ImI0OWM5N2E0NTJkYzg0N2I4N2ZmY2EyMzNkODc4YTJhZTQ4MDc3Yzc3ZWUxNzA1YWMxOTI1NDY3ZTJhOThiZjAiLCJ0eXBlIjoxMH0.bcEc8FzDj0J6LQGaIFHUlRGWHH_KSllHo6QFnFB8mTk")
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