package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class SkipPageObject extends MainPageObject {

    public SkipPageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button";

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


}
