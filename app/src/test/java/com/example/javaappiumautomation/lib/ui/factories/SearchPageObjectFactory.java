package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidSearchPageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSSearchPageObject;

import io.appium.java_client.AppiumDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);

        } else {
            return new iOSSearchPageObject(driver);

        }


    }

}
