package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK;

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickMyLists(){

        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My list",
                5
        );

    }


}
