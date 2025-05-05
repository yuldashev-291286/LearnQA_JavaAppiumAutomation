package com.example.javaappiumautomation.lib.ui.mobile_web;

import com.example.javaappiumautomation.lib.ui.AuthorizationPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MWAuthorizationPageObject extends AuthorizationPageObject {

    static {
        MAIN_MENU = "xpath://input[@id='main-menu-input']";
        LOGIN_BUTTON = "xpath://span[contains(text(),'Log in')]";
        LOGIN_INPUT = "xpath://input[@class='loginText mw-userlogin-username cdx-text-input__input']";
        PASSWORD_INPUT = "xpath://input[@class='loginPassword mw-userlogin-password cdx-text-input__input']";
        SUBMIT_BUTTON = "xpath://button[@class='mw-htmlform-submit cdx-button cdx-button--weight-primary cdx-button--action-progressive']";
        EXPECTED_PAGE_TITLE = "xpath://h1[contains(text(),'Welcome, YuldashevRuslan!')]";

    }

    public MWAuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }


}
