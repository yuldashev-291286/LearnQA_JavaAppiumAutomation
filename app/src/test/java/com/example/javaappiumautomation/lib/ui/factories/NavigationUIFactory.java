package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.NavigationUI;
import com.example.javaappiumautomation.lib.ui.android.AndroidNavigationUI;
import com.example.javaappiumautomation.lib.ui.ios.iOSNavigationUI;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWNavigationUI;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);

        } else if (Platform.getInstance().isIOS()) {
            return new iOSNavigationUI(driver);

        } else {
            return new MWNavigationUI(driver);

        }

    }

}
