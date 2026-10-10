import controller.UserController;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.*;
import static io.restassured.RestAssured.given;

public class UserTests {

    private final UserController userController =  new UserController();

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

    @Test
    void createUserTest2() {
        Response response = userController.createUser(DEFAULT_USER);

        Assertions.assertEquals(200, response.jsonPath().getInt("code"));
        Assertions.assertEquals("unknown", response.jsonPath().getString("type"));
        Assertions.assertNotEquals("0", response.jsonPath().getString("message"));
    }

    @Test
    void createUserTest3() {
        Response response = userController.createUser(INVALID_USER);

        Assertions.assertEquals(200, response.jsonPath().getInt("code"));
        Assertions.assertEquals("unknown", response.jsonPath().getString("type"));
        Assertions.assertEquals("0", response.jsonPath().getString("message"));
    }

    @Test
    void updateUserTest() {
        Response response = userController.createUser(DEFAULT_USER);

    }
}
