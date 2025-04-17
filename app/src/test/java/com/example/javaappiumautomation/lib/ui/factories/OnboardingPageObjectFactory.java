package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidOnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSOnboardingPageObject;

import io.appium.java_client.AppiumDriver;

public class OnboardingPageObjectFactory {

    public static OnboardingPageObject get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidOnboardingPageObject(driver);
        } else {
            return new iOSOnboardingPageObject(driver);
        }

    }

}
