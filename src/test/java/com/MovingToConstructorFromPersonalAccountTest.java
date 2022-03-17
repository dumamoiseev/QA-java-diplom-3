package com;

import com.apiActions.User;
import com.apiActions.UserClient;
import com.apiActions.UserCredentials;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.apiActions.ApiClient.loginURL;
import static com.apiActions.ApiClient.mainPageURL;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class MovingToConstructorFromPersonalAccountTest extends BaseTest {

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
        MainPage mainPage = Selenide.open(mainPageURL, MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = Selenide.page(PersonalAccountPage.class);
        personalAccountPage.clickExitButton();

        webdriver().driver().close();

        String accessToken = userCredentials.getUserAccessToken(user);
        userClient.delete(accessToken);
    }

    @DisplayName("Переход в Конструктор по клику на логотип Stellar Burgers из Личного кабинета")
    @Test
    public void moveToConstructorByClickOnConstructorButtonTest() {

        LoginPage loginPage = Selenide.open(loginURL, LoginPage.class);
        loginPage.logIn(user.email, user.password);

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = Selenide.page(PersonalAccountPage.class);
        personalAccountPage.clickConstructorButton();
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();

        assertEquals("Пользователь не перешел на страницу 'Конструктор'", mainPageURL, actualUrl);
    }

    @DisplayName("Переход Конструктор по клику на логотип Stellar Burgers из Личного кабинета")
    @Test
    public void moveToConstructorByClickOnLogoTest() {

        LoginPage loginPage = Selenide.open(loginURL, LoginPage.class);
        loginPage.logIn(user.email, user.password);

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = Selenide.page(PersonalAccountPage.class);
        personalAccountPage.clickStellarBurgerHeaderLogo();
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();

        assertEquals("Пользователь не перешел на страницу 'Конструктор'", mainPageURL, actualUrl);
    }
}
