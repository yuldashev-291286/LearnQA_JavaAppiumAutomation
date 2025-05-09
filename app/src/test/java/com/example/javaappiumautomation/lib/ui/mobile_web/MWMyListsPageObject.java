package com.example.javaappiumautomation.lib.ui.mobile_web;

import com.example.javaappiumautomation.lib.ui.MyListsPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}'";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(class,watched)]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
