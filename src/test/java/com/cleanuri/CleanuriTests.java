package com.cleanuri;

import com.cleanuri.clients.CleanuriClient;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CleanuriTests {
    private static final String EXPECTED_RESULT = "https://cleanuri.com";
    private CleanuriClient cleanuriClient = new CleanuriClient();

    @BeforeEach
    public void turnOnLogs() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PositiveTests.csv")
    public void positiveTests(String multiParValue) {
        String response = cleanuriClient.positiveTestsClient(multiParValue);
        assertThat(response).startsWith(EXPECTED_RESULT);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NegativeTests.csv")
    public void negativeTests(String multiPartKey, String multiParValue, String expectedResult) {
       String response = cleanuriClient.negativeTestsClient(multiPartKey, multiParValue);
       assertThat(response).isEqualTo(expectedResult);
    }

    @AfterEach
    public void resetLogs() {
        RestAssured.reset();
    }
}
