package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidOnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSOnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWOnboardingPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class OnboardingPageObjectFactory {

    public static OnboardingPageObject get(RemoteWebDriver driver) throws Exception{

        if (Platform.getInstance().isAndroid()){
            return new AndroidOnboardingPageObject(driver);

        } else if (Platform.getInstance().isIOS()){
            return new iOSOnboardingPageObject(driver);
        }
        else {
            return new MWOnboardingPageObject(driver);

        }

    }

}
