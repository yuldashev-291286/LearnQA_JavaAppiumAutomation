package com.example.javaappiumautomation.lib.ui.ios;

import com.example.javaappiumautomation.lib.ui.NavigationUI;

import io.appium.java_client.AppiumDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    }

    public iOSNavigationUI(AppiumDriver driver){
        super(driver);

    }

}
