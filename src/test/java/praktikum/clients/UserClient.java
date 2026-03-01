package praktikum.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.api.dto.UserRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String DELETE_PATH = "/api/auth/user";

    @Step("Создание пользователя через API с проверкой 200 и возвратом токена: {user.email} / {user.password} / {user.name}")
    public String createUser(UserRequest user) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_PATH);

        response.then()
                .statusCode(200)
                .body("success", is(true));

        return response.path("accessToken");
    }

    @Step("Логин пользователя через API с проверкой 200 и возвратом токена: {user.email} / {user.password}")
    public String loginUser(UserRequest user) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_PATH);

        response.then()
                .statusCode(200)
                .body("success", is(true));

        return response.path("accessToken");
    }

    @Step("Удаление пользователя через API с проверкой 202")
    public void deleteUser(String token) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .when()
                .delete(DELETE_PATH)
                .then()
                .statusCode(202)
                .body("success", is(true));
    }
}