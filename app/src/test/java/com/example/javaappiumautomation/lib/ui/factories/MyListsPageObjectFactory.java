package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.MyListsPageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidMyListsPageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSMyListsPageObject;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        } else {
            return new iOSMyListsPageObject(driver);
        }

    }

}
