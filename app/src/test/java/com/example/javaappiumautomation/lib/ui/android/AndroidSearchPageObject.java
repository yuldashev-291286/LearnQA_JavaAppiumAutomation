package com.example.javaappiumautomation.lib.ui.android;

import com.example.javaappiumautomation.lib.ui.SearchPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
                SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRING}']";
                SEARCH_RESULT_BY_CONTAINS_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'{SUBSTRING}')]";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_IS_EMPTY = "id:org.wikipedia:id/search_container";
                SEARCH_RESULT_LIST = "id:org.wikipedia:id/search_results_list";
                SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
                NON_EXISTENT_ELEMENT = "id:org.wikipedia:id/page_title";
                SELECT_ITEM_WITH_TITLE_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'{SUBSTRING}')]";
                SELECT_ITEM_WITH_DESCRIPTION_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and contains(@text,'{SUBSTRING}')]";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }


}
