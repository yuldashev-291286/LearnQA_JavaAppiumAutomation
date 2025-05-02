package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.MyListsPageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidMyListsPageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSMyListsPageObject;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWMyListsPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);

        } else if (Platform.getInstance().isIOS()) {
            return new iOSMyListsPageObject(driver);

        } else {
            return new MWMyListsPageObject(driver);

        }

    }

}
