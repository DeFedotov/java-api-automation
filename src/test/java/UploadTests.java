import controller.PetController;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UploadTests {

    private final PetController petController = new PetController();


    @Test
    void apiUploadTest(){
        String url = "https://petstore.swagger.io/v2/pet/1/uploadImage";

        File file = new File("src/main/resources/upload_test.jpeg");

        Response response =
                given()
                        .header("accept", "application/json")
                        .contentType("multipart/form-data")
                        .multiPart("file", file, "image/jpeg")
                .when()
                        .post(url)
                .then()
                        .statusCode(200)
                        .extract()
                        .response();
        System.out.println("Response: " + response.asString());
    }

    @Test
    void apiUploadTest2() {
        URL resource = getClass().getClassLoader().getResource("upload_test.jpeg");
        assert resource != null;
        File file = new File(resource.getFile());

        given()
                .baseUri("https://petstore.swagger.io/v2")
                .header("accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("file", file, "image/jpeg")
                .log().all()
        .when()
                .post("/pet/1/uploadImage")
        .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", notNullValue());
    }

    @Test
    void apiUploadTest3() {
        // Arrange
        long petId = 1;
        URL resource = getClass().getClassLoader().getResource("upload_test.jpeg");
        assert resource != null;
        File file = new File(resource.getFile());

        // Act
        Response response = petController.uploadImage(petId, file);

        // Assert
        Assertions.assertEquals(200, response.statusCode());
    }
}
