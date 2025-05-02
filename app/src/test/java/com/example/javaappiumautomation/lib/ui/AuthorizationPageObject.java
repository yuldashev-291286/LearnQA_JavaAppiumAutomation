package com.example.javaappiumautomation.lib.ui;

import org.apache.bcel.generic.PUSH;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath:",
            LOGIN_INPUT = "xpath:",
            PASSWORD_INPUT = "xpath:",
            SUBMIT_BUTTON = "xpath:";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);

    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, login, "Cannot find and put a password to the password input", 5);

    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);

    }


}
