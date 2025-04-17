package com.example.javaappiumautomation.lib.ui.android;

import com.example.javaappiumautomation.lib.ui.MyListsPageObject;

import io.appium.java_client.AppiumDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
       FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}'";
       ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}'";
    }

    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
    }

}
