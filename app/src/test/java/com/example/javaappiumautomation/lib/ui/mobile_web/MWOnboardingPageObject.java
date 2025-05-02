
package com.example.javaappiumautomation.lib.ui.mobile_web;

import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MWOnboardingPageObject extends OnboardingPageObject {

    static {
        SKIP_BUTTON = "xpath:";
        PRIMARY_TEXT_VIEW = "xpath:";
        ACCEPT_BUTTON = "xpath:";
        REJECT_BUTTON = "xpath:";
    }

    public MWOnboardingPageObject(RemoteWebDriver driver){
        super(driver);

    }

}