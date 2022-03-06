package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    // кнопка Войти
    @FindBy(how = How.CSS,using = ".button_button_size_medium__3zxIa")
    public SelenideElement enterButton;

    //поле ввода email
    @FindBy(how = How.CSS,using = "fieldset:nth-child(1) input")
    public SelenideElement emailInput;

    //поле ввода password
    @FindBy(how = How.CSS,using = "fieldset:nth-child(2) input")
    public SelenideElement passwordInput;

    //кнопка "Восстановить пароль"
    @FindBy(how = How.XPATH,using = "//a[@href='/forgot-password']")
    public SelenideElement recoverPassButton;

    //title "Вход"
    @FindBy(how = How.XPATH,using = "//h2[text()='Вход']")
    private SelenideElement enterTitle;

    //получить текст enterTitle
    public String EnterTitle() {
        return enterTitle.shouldBe(Condition.exist).getText();
    }

    //скролить до кнопки recoverPassButton
    public void scrollToRecoverPassButton() {
        recoverPassButton.scrollTo();
    }

    //клик по кнопке recoverPassButton
    public void clickRecoverPassButton() {
        recoverPassButton.click();
    }

    //заполнить поле password
    public void setPassInput(String password) {
        passwordInput.setValue(password);
    }

    //заполнить поле email
    public void setEmailInput(String email) {
        emailInput.setValue(email);
    }

    //клик по enterButton
    public void clickEnterButton() {
        enterButton.click();
    }

    //Логинимся
    public void logIn(String email, String pass) {
        setEmailInput(email);
        setPassInput(pass);
        clickEnterButton();
    }

}
