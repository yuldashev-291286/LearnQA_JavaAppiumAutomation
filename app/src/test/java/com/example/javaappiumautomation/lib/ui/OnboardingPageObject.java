package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

abstract public class OnboardingPageObject extends MainPageObject {

    public OnboardingPageObject(RemoteWebDriver driver){
        super(driver);
    }

    protected static String
            SKIP_BUTTON,
            PRIMARY_TEXT_VIEW,
            ACCEPT_BUTTON,
            REJECT_BUTTON;

    public void waitSkipAndClick(){

        this.waitForElementPresent(
                SKIP_BUTTON,
                "Cannot find 'Skip' button",
                15
        );

        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find 'Skip' button",
                5
        );

    }

    public void onboardingHomePage(){

        this.waitForElementPresent(
                PRIMARY_TEXT_VIEW,
                "Wikipedia home page not found",
                5
        );

    }

    public String actualTitle(){
        return driver
                .findElement(By.id("org.wikipedia:id/primaryTextView"))
                .getAttribute("text");
    }

    public void swipeElementToLeft(){
        this.swipeElementToLeft(
                PRIMARY_TEXT_VIEW,
                "Failed to proceed to next page"
        );

    }

    public void waitAndClickAcceptButton(){

        this.waitForElementPresent(
                ACCEPT_BUTTON,
                "There are no buttons to go to the main page",
                5
        );

        this.waitForElementPresent(
                REJECT_BUTTON,
                "There are no buttons to go to the main page",
                5
        );

        // Нажимает кнопку: "Accept" на Android и “Get started” на iOS в конце Onboarding.
        this.waitForElementAndClick(
                ACCEPT_BUTTON,
                "There are no buttons to go to the main page",
                5
        );

    }


}
