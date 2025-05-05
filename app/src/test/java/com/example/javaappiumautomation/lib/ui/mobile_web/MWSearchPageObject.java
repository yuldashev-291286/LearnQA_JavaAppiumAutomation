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

        SELECT_ITEM_LIST_WEB = "xpath://div[contains(text(),'Object-oriented programming language')]";
        NON_EXISTENT_ELEMENT_WEB = "xpath://div/t";
        RETURN_START_PAGE = "xpath://div[@class='header-action']/button[@type='button']";
        EXPECTED_PAGE_TITLE = "xpath://h1[contains(text(),'Welcome, YuldashevRuslan!')]";
        ADD_FOUND_ARTICLE_TO_FAVORITES = "xpath://li[@title='{SUBSTRING}']/a[@type='button']";
        FAVORITES_BUTTON = "xpath://span[contains(text(),'Watchlist')]";
        DELETE_SAVED_ARTICLE = "xpath://li[@title='JavaScript']/a[@type='button']";
        FAVORITES_NOT_EMPTY = "xpath://ul[@class='content-unstyled mw-mf-page-list thumbs page-summary-list mw-mf-watchlist-page-list']";
        REST_ARTICLE = "xpath://h3[contains(text(),'Java (programming language)')]";
        REST_ARTICLE_HREF = "xpath://a[@href='/wiki/Java_(programming_language)']";

    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
