package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {


    // Ввод имени
    @FindBy(how = How.CSS,using = "fieldset:nth-child(1) input")
    private SelenideElement nameInput;

    //Ввод e-mail
    @FindBy(how = How.CSS,using = "fieldset:nth-child(2) input")
    private SelenideElement emailInput;

    // Ввод пароля
    @FindBy(how = How.XPATH,using = "//input[@type='password']")
    private SelenideElement passInput;

    // кнопка "Зарегистрироваться"
    @FindBy(how = How.CSS,using = ".button_button__33qZ0")
    private SelenideElement signUpButton;

    // сообщение "Некорректный пароль"
    @FindBy(how = How.CSS,using = ".input__error")
    private SelenideElement incorrectShortPasswordErrorMessage;

    public String getTooShortPasswordErrorMessage() {
        return incorrectShortPasswordErrorMessage.getText();
    }

    //кнопка "Войти"
    @FindBy(how = How.CSS,using = ".Auth_link__1fOlj")
    private SelenideElement registrationPageEnterButton;

    public void scrollToRegistrationPageEnterButton() {
        registrationPageEnterButton.scrollTo();
    }

    public void clickRegistrationPageEnterButton() {
        registrationPageEnterButton.click();
    }

    // Ввод имени
    public void setNameInput(String name) {
        nameInput.setValue(name);
    }

    // Вавод e-mail
    public void setEmailInput(String email) {
        emailInput.setValue(email);
    }

    // Вавод пароля
    public void setPassInput(String password) {
        passInput.setValue(password);
    }

    // нажать кнопку "Зарегистрироваться"
    public void clickSignUpButton() {
        signUpButton.shouldBe(Condition.enabled).click();
    }

    public void signUp(String name, String email, String password) {
        setNameInput(name);
        setEmailInput(email);
        setPassInput(password);
        clickSignUpButton();
    }

}
