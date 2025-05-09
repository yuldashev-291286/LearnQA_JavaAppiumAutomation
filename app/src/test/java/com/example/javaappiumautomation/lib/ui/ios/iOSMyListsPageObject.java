package com.example.javaappiumautomation.lib.ui.ios;

import com.example.javaappiumautomation.lib.ui.MyListsPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}'";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}'";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
