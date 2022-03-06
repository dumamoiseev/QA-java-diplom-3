package com.ApiActions;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient extends ApiClient {

    @Step("Регистрация курьера")
    public Response userRegistration(User user){
        return given()
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post("/auth/register");
    }


}
