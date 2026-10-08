import controller.FluentUserController;
import lombok.Data;
import org.junit.jupiter.api.*;

import static constants.CommonConstants.DEFAULT_USER;

public class FluentControllerTests {
    FluentUserController fluentController = new FluentUserController();

    @BeforeEach
    @AfterEach
    void clear(){
        fluentController.deleteUserByName(DEFAULT_USER.getUserName());
    }

    @Test
    @DisplayName("Check add user returns 200 status ok")
    void addUserSuccess(){
        fluentController.addUser(DEFAULT_USER).statusCode(200);
    }
}
