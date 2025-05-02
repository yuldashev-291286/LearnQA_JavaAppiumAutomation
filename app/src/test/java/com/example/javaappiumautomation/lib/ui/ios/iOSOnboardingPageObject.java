package com.example.javaappiumautomation.lib.ui.ios;

import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class iOSOnboardingPageObject extends OnboardingPageObject {

    static {
        SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
        PRIMARY_TEXT_VIEW = "id:org.wikipedia:id/primaryTextView";
        ACCEPT_BUTTON = "id:org.wikipedia:id/acceptButton";
        REJECT_BUTTON = "id:org.wikipedia:id/rejectButton";
    }

    public iOSOnboardingPageObject(RemoteWebDriver driver){
        super(driver);

    }

}
