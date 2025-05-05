package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.WelcomePageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSWelcomePageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObjectFactory {

    public static WelcomePageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().isMW()){
            return new iOSWelcomePageObject(driver);

        }
        return null;
    }

}
