package com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage {

    // кнопка Конструктор
    @FindBy(how = How.XPATH,using = "//p[text()='Конструктор']")
    public SelenideElement constructorButton;

    public void clickConstructorButton() {
        constructorButton.click();
    }

    // Лого Stellar burger header
    @FindBy(how = How.CSS,using = ".AppHeader_header__logo__2D0X2")
    public SelenideElement headerLogo;

    public void clickStellarBurgerHeaderLogo() {
        headerLogo.click();
    }

    // кнопка Профиль
    @FindBy(how = How.XPATH,using = "//a[text()='Профиль']")
    public SelenideElement profileButton;

    public boolean isProfileButtonDisplayed() {
        return profileButton.shouldBe(Condition.exist).isDisplayed();
    }

    // кнопка Выход

    @FindBy(how = How.CSS,using = ".Account_button__14Yp3")
    public SelenideElement exitButton;

    public boolean isExitButtonDisplayed() {
        return exitButton.shouldBe(Condition.exist).isDisplayed();
    }

    public void clickExitButton() {
        exitButton.click();
    }

}

