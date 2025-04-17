package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.NavigationUI;
import com.example.javaappiumautomation.lib.ui.android.AndroidNavigationUI;
import com.example.javaappiumautomation.lib.ui.ios.iOSNavigationUI;

import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        } else {
            return new iOSNavigationUI(driver);
        }

    }

}
