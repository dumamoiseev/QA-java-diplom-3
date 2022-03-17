package com;

import com.apiActions.User;
import com.apiActions.UserClient;
import com.apiActions.UserCredentials;
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
import static org.junit.Assert.assertTrue;

public class PersonalProfileEnterTest {

    User user;
    UserClient userClient;
    UserCredentials userCredentials;

    @Before
    public void setUp() {
        userClient = new UserClient();
        userCredentials = new UserCredentials();
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

        String accessToken = userCredentials.getUserAccessToken(user);
        userClient.delete(accessToken);
    }

    @DisplayName("Вход в аккаунт пользователя")
    @Test
    public void successfulEnterIntoUserAccountTest() {

        LoginPage loginPage = Selenide.open(loginURL, LoginPage.class);
        loginPage.logIn(user.email, user.password);

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = Selenide.page(PersonalAccountPage.class);
        boolean isProfileLinkDisplayed = personalAccountPage.isProfileButtonDisplayed();
        personalAccountPage.clickExitButton();

        assertTrue("Пользователь не перешел на личную страницу", isProfileLinkDisplayed);
    }
}

