package constants;

import models.User;

public class CommonConstants {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final User DEFAULT_USER = User.builder()
            .id(1L)
            .username("username1")
            .firstName("firstName1")
            .lastName("lastName1")
            .password("password1")
            .email("email")
            .phone("phone")
            .userStatus(1)
            .build();
    public static final User INVALID_USER = User.builder()
            .build();

    public static final User UPDATED_USER = User.builder()
            .id(222L)
            .username("username1")
            .firstName("firstName2")
            .lastName("lastName2")
            .build();
}
