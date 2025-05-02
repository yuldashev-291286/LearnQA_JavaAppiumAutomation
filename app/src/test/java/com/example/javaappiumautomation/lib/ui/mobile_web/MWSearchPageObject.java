package com.example.javaappiumautomation.lib.ui.mobile_web;

import com.example.javaappiumautomation.lib.ui.SearchPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://input[@class='search skin-minerva-search-trigger']";
        SEARCH_INIT_ELEMENT_WEB = "xpath://input[@class='search mf-icon-search']";
        SEARCH_WIKIPEDIA_PLACEHOLDER = "xpath://input[contains(@placeholder,'Search Wikipedia')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://ul[@class='mw-mf-page-list thumbs actionable']/li[@title='{SUBSTRING}']";
        SEARCH_RESULT_BY_CONTAINS_SUBSTRING_TPL = "xpath://ul[@class='mw-mf-page-list thumbs actionable']/li[@title='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "xpath://span[@class='mf-icon mf-icon-clear mf-icon--small ']";
        SEARCH_RESULT_IS_EMPTY = "xpath://div[contains(@style,'display: none;')]";
        SEARCH_RESULT_LIST = "xpath://ul[@class='mw-mf-page-list thumbs actionable']/li";
        SEARCH_RESULT_ELEMENT = "xpath://ul[@class='mw-mf-page-list thumbs actionable']/li";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://div[@class='caption']";
        SELECT_ITEM_WITH_TITLE_SUBSTRING_TPL = "xpath://ul[@class='mw-mf-page-list thumbs actionable']/li[@title='{SUBSTRING}']";
        SELECT_ITEM_WITH_DESCRIPTION_SUBSTRING_TPL = "xpath://div[contains(text(),'{SUBSTRING}')]";

    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
