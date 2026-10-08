package controller;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;

public class HttpResponse {
    private final ValidatableResponse response;

    public HttpResponse(ValidatableResponse response) {
        this.response = response;
    }

    @Step("Check status code")
    public HttpResponse statusCode(int statusCode) {
        this.response.statusCode(statusCode);
        return this;
    }

    @Step("Check json value by path '{path}' and expected value '{expectedValue}'")
    public HttpResponse jsonValue(String path, String expectedValue) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertEquals(expectedValue, actualValue);
        return this;
    }

    @Step("Check json value is not null")
    public HttpResponse jsonValueIsNotNull(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertNotNull(actualValue);
        return this;
    }

    @Step("Check json value is null")
    public HttpResponse jsonValueIsNull(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertNull(actualValue);
        return this;
    }

    @Step("Get json value by path {path}")
    public String getJsonValue(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        Assertions.assertNotNull(actualValue);
        return actualValue;
    }

    @Step("Return info about response")
    public String toString() {
        return String.format("Status code: %s and response: \n%s", response.extract().response().statusCode(), response.extract().response().body().asString());
    }
}
