package controller;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import models.User;

import static constants.CommonConstants.*;
import static io.restassured.RestAssured.given;

public class FluentUserController {
    RequestSpecification requestSpecification = given();

    public FluentUserController() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
        this.requestSpecification.accept(ContentType.JSON);
        this.requestSpecification.baseUri(BASE_URL);
        this.requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Add default user")
    public HttpResponse addDefaultUser() {
        this.requestSpecification.body(DEFAULT_USER);
        return new HttpResponse(given(this.requestSpecification).post("user").then());
    }

    @Step("Add user")
    public HttpResponse addUser(User user) {
        this.requestSpecification.body(user);
        return new HttpResponse(given(this.requestSpecification).post("user").then());
    }

    @Step("Get user by name")
    public HttpResponse getUserByName(String name) {
        return new HttpResponse(given(this.requestSpecification).get(String.format("user/" + name)).then());
    }

    @Step("Delete user by name")
    public HttpResponse deleteUserByName(String name) {
        return new HttpResponse(given(this.requestSpecification).delete(String.format("user/%s", name)).then());
    }
}
