package com;

import com.apiActions.User;
import com.apiActions.UserClient;
import com.codeborne.selenide.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static com.apiActions.ApiClient.registerPageURL;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertEquals;

import com.codeborne.selenide.Selenide;
import org.junit.Before;

public class RegistrationUserTest extends BaseTest {

    UserClient userClient;
    User user;
    String expectedInvalidPassErrorMessage = "Некорректный пароль";
    String expectedEnterTitle = "Вход";

    @Before
    public void setUp() {
        userClient = new UserClient();
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        webdriver().driver().close();
    }

    @DisplayName("Проверяем, что можно зарегистрироваться с паролем из 6 символов")
    @Test
    public void signUpWithSixCharacterPasswordPositiveTest() {
        user = User.getRandomCorrectUser();
        RegisterPage registerPage = Selenide.open(registerPageURL, RegisterPage.class);
        registerPage.signUp(user.name, user.email, user.password);

        LoginPage loginPage = Selenide.page(LoginPage.class);
        String actualEnterTitle = loginPage.EnterTitle();

        assertEquals("Expected title is " + expectedEnterTitle + ". But actual is " + actualEnterTitle,
                expectedEnterTitle, actualEnterTitle);
    }

    @DisplayName("Проверяем, что можно зарегистрироваться с паролем из 7 символов")
    @Test
    public void signUpWithSevenSymbolsPassPositiveTest() {
        user = User.getUserWith7lettersPassword();
        RegisterPage registerPage = Selenide.open(registerPageURL, RegisterPage.class);
        registerPage.signUp(user.name, user.email, user.password);

        LoginPage loginPage = Selenide.page(LoginPage.class);
        String actualEnterTitle = loginPage.EnterTitle();

        assertEquals("Expected title is " + expectedEnterTitle + ". But actual is " + actualEnterTitle,
                expectedEnterTitle, actualEnterTitle);
    }

    @DisplayName("Проверяет, что нельзя зарегистрироваться с паролем из 5 символов")
    @Test
    public void signUpWithFiveCharacterPassNegativeTest() {
        user = User.getUserWith5lettersPassword();
        RegisterPage registerPage = Selenide.open(registerPageURL, RegisterPage.class);
        registerPage.signUp(user.name, user.email, user.password);

        String actualErrorMessage = registerPage.getTooShortPasswordErrorMessage();

        assertEquals("Expected error message is " + expectedInvalidPassErrorMessage + ". But actual is " + actualErrorMessage,
                expectedInvalidPassErrorMessage, actualErrorMessage);
    }
}
