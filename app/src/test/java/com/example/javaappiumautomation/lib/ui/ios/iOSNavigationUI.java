package com.example.javaappiumautomation.lib.ui.ios;

import com.example.javaappiumautomation.lib.ui.NavigationUI;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public iOSNavigationUI(RemoteWebDriver driver){
        super(driver);

    }

}
