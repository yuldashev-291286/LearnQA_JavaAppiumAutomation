package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidSearchPageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSSearchPageObject;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWSearchPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);

        } else if (Platform.getInstance().isIOS()){
            return new iOSSearchPageObject(driver);

        } else {
            return new MWSearchPageObject(driver);
        }

    }

}
