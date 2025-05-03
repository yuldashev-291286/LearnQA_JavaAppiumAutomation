package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            MAIN_MENU = "xpath://input[@id='main-menu-input']",
            LOGIN_BUTTON = "xpath://span[contains(text(),'Log in')]",
            LOGIN_INPUT = "xpath://input[@class='loginText mw-userlogin-username cdx-text-input__input']",
            PASSWORD_INPUT = "xpath://input[@class='loginPassword mw-userlogin-password cdx-text-input__input']",
            SUBMIT_BUTTON = "xpath://button[@class='mw-htmlform-submit cdx-button cdx-button--weight-primary cdx-button--action-progressive']",
            EXPECTED_PAGE_TITLE = "xpath://h1[contains(text(),'Welcome, YuldashevRuslan!')]";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickMainMenu(){
        this.waitForElementPresent(MAIN_MENU, "Cannot find main_menu button", 5);
        this.waitForElementAndClick(MAIN_MENU, "Cannot find main_menu button", 5);
    }
    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);

    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the password input", 5);

    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit auth button", 5);

    }

    public void checkExpectedPageTitle(){
        this.waitForElementPresent(EXPECTED_PAGE_TITLE, "Cannot find expected title");

    }


}
