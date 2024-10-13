package com.cleanuri.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Spec {
    protected static final String BASE_URI = "https://cleanuri.com/";
    protected static final String ENDPOINT = "/api/v1/shorten";

    protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.MULTIPART)
                .build();
    }
}
