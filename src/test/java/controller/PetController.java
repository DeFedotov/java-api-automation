package controller;

import io.restassured.response.Response;
import java.io.File;

import static constants.CommonConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class PetController {
    private static final String PET_ENDPOINT = "pet";

    public Response uploadImage(long petId, File file) {
        return
            given()
                .header("accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("file", file, "image/jpeg")
            .when()
                .post(BASE_URL + PET_ENDPOINT + "/" + petId + "/uploadImage")
                .andReturn();
    }
}
