package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    protected static String
            MAIN_MENU,
            LOGIN_BUTTON,
            LOGIN_INPUT,
            PASSWORD_INPUT,
            SUBMIT_BUTTON,
            EXPECTED_PAGE_TITLE;

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickMainMenu(){
        this.waitForElementPresent(
                MAIN_MENU,
                "Cannot find main_menu button",
                5);
        this.waitForElementAndClick(
                MAIN_MENU,
                "Cannot find main_menu button",
                5);
    }
    public void clickAuthButton() {
        this.waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find auth button",
                5);
        this.waitForElementAndClick(
                LOGIN_BUTTON,
                "Cannot find and click auth button",
                5);

    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(
                LOGIN_INPUT,
                login,
                "Cannot find and put a login to the login input",
                5);
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                password,
                "Cannot find and put a password to the password input",
                5);

    }

    public void submitForm(){
        this.waitForElementAndClick(
                SUBMIT_BUTTON,
                "Cannot find and click submit auth button",
                5);

    }

    public void checkExpectedPageTitle(){
        this.waitForElementPresent(
                EXPECTED_PAGE_TITLE,
                "Cannot find expected title");

    }


}
