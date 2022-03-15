package com;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;


public class BaseTest {

    @Before
    public void startUp() {
        String browserType = "chrome";

        Configuration.browser = System.getProperty("selenide.browser", browserType);
        System.setProperty("selenide.browser", browserType);
        WebDriverManager.chromedriver();
    }
}
