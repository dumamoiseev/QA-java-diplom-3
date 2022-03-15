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


import static com.apiActions.ApiClient.*;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class LogInUserTest extends BaseTest {

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
        MainPage mainPage = Selenide.open(mainPageURL, MainPage.class);
        mainPage.clickPersonalAccountButton();

        PersonalAccountPage personalAccountPage = Selenide.page(PersonalAccountPage.class);
        personalAccountPage.clickExitButton();

        webdriver().driver().close();
    }


    @DisplayName("Логин при клике на кнопку Войти на Главной странице")
    @Test
    public void logInByClickingEnterToAccountButtonTest() {

        MainPage mainPage = Selenide.open(mainPageURL, MainPage.class);
        mainPage.clickLogInToAccountButton();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.logIn(user.email, user.password);

        boolean setUpBurgerTitle = mainPage.isSetUpBurgerTitleDisplayed();
        assertTrue("Logo 'Собери бургер' is not displayed", setUpBurgerTitle);
    }

    @DisplayName("Логин при клике на кнопку Личный Аккаунт на Главной странице")
    @Test
    public void logInByClickingPersonalAccountButtonTest() {

        MainPage mainPage = Selenide.open(mainPageURL, MainPage.class);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.logIn(user.email, user.password);

        mainPage.scroolToSetUpBurgerTitle();
        boolean setUpBurgerTitle = mainPage.isSetUpBurgerTitleDisplayed();
        assertTrue("Logo 'Собери бургер' is not displayed", setUpBurgerTitle);
    }

    @DisplayName("Логин при клике на кнопку Войти на странице Регистрации")
    @Test
    public void logInByClickingEnterButtonOnRegistrationPageTest() {

        RegisterPage registerPage = Selenide.open(registerPageURL, RegisterPage.class);
        registerPage.scrollToRegistrationPageEnterButton();
        registerPage.clickRegistrationPageEnterButton();

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.logIn(user.email, user.password);

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.scroolToSetUpBurgerTitle();
        boolean setUpBurgerTitle = mainPage.isSetUpBurgerTitleDisplayed();

        assertTrue("Лого 'Собери бургер' не отображается", setUpBurgerTitle);
    }

    @DisplayName("Логин при клике на кнопку Войти на странице Восстановления Пароля")
    @Test
    public void logInByClickingEnterButtonOnForgotPasswordPageTest() {

        LoginPage loginPage = Selenide.open(loginURL, LoginPage.class);
        loginPage.scrollToRecoverPassButton();
        loginPage.clickRecoverPassButton();

        ForgotPasswordPage forgotPasswordPage = Selenide.page(ForgotPasswordPage.class);
        forgotPasswordPage.clickEnterButtonOnForgotPassPage();

        loginPage.logIn(user.email, user.password);

        MainPage mainPage = Selenide.page(MainPage.class);
        mainPage.scroolToSetUpBurgerTitle();
        boolean setUpBurgerTitle = mainPage.isSetUpBurgerTitleDisplayed();

        assertTrue("Лого 'Собери бургер' не отображается", setUpBurgerTitle);
    }
}

