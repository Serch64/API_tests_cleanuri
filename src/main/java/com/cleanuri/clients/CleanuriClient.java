package com.cleanuri.clients;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

public class CleanuriClient extends Spec {
    private static final String KEY_MULTIPART = "url";
    private static final String JSON_PATH = "result_url";
    private static final String ERROR_JSON_PATH = "error";

    public String positiveTestsClient(String multiParValue) {
        return given()
                .spec(getSpec())
                .multiPart(KEY_MULTIPART, multiParValue)
                .when()
                .post(ENDPOINT)
                .then()
                .statusCode(SC_OK)
                .extract()
                .jsonPath()
                .getString(JSON_PATH);
    }
    public String negativeTestsClient(String multiPartKey, String multiParValue) {
        return given()
                .spec(getSpec())
                .multiPart(multiPartKey, multiParValue)
                .when()
                .post(ENDPOINT)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .jsonPath()
                .getString(ERROR_JSON_PATH);
    }
}