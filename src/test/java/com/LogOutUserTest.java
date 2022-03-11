package com;

import com.apiActions.User;
import com.apiActions.UserClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.apiActions.ApiClient.loginURL;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class LogOutUserTest extends BaseTest {
    String expectedEnterTitle = "Вход";
    UserClient userClient;
    User user;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandomCorrectUser();
        Response response = userClient.userRegistration(user);
        response.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        webdriver().driver().close();
    }

    @DisplayName("Проверяет, что пользователь может разлогиниться")
    @Test
    public void logOutPositiveTest() {

        LoginPage loginPage = Selenide.open(loginURL, LoginPage.class);
        loginPage.logIn(user.email, user.password);

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = Selenide.page(PersonalAccountPage.class);
        personalAccountPage.clickExitButton();

        String actualEnterTitle = loginPage.EnterTitle();

        assertEquals("Пользователя не перевело на страницу авторизации", expectedEnterTitle, actualEnterTitle);
    }
}
