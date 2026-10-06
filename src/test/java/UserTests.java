import controller.UserController;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserTests {

    @Test
    void createUserTest() {
        String body = """
                {
                    "id": 0,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                }
                """;
        given().
                baseUri(BASE_URL).
                accept("application/json").
                contentType("application/json").
                body(body).
                log().all().
        when().
                post("/v2/user").
                then().
                statusCode(200).
                log().all().
                    body("code", Matchers.equalTo(200),
                        "type", Matchers.equalTo("unknown"),
                        "message", Matchers.notNullValue());;
    }
}
