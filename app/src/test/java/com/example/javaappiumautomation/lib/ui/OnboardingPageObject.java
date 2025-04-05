package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class OnboardingPageObject extends MainPageObject {

    public OnboardingPageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
            PRIMARY_TEXT_VIEW = "org.wikipedia:id/primaryTextView",
            ACCEPT_BUTTON = "org.wikipedia:id/acceptButton",
            REJECT_BUTTON = "org.wikipedia:id/rejectButton";


    public void waitSkipAndClick(){

        this.waitForElementPresent(
                By.id(SKIP_BUTTON),
                "Cannot find 'Skip' button",
                15
        );

        this.waitForElementAndClick(
                By.id(SKIP_BUTTON),
                "Cannot find 'Skip' button",
                5
        );

    }

    public void onboardingHomePage(){

        this.waitForElementPresent(
                By.id(PRIMARY_TEXT_VIEW),
                "Wikipedia home page not found",
                5
        );

    }

    public String actualTitle(){
        return driver
                .findElement(By.id(PRIMARY_TEXT_VIEW))
                .getAttribute("text");
    }

    public void swipeElementToLeft(){
        this.swipeElementToLeft(
                By.id(PRIMARY_TEXT_VIEW),
                "Failed to proceed to next page"
        );

    }

    public void waitAndClickAcceptButton(){

        this.waitForElementPresent(
                By.id(ACCEPT_BUTTON),
                "There are no buttons to go to the main page",
                5
        );

        this.waitForElementPresent(
                By.id(REJECT_BUTTON),
                "There are no buttons to go to the main page",
                5
        );

        // Нажимает кнопку: "Accept" на Android и “Get started” на iOS в конце Onboarding.
        this.waitForElementAndClick(
                By.id(ACCEPT_BUTTON),
                "There are no buttons to go to the main page",
                5
        );

    }


}
