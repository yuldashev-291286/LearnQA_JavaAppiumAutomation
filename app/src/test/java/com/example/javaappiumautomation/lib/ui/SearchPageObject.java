package com.example.javaappiumautomation.lib.ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            //SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_LIST = "org.wikipedia:id/search_results_list",
            SEARCH_RESULT_ELEMENT = "org.wikipedia:id/page_list_item_title",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    // TEMPLATES METHODS
    private static String getResultSearchElement(String subString){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    // TEMPLATES METHODS

    public void initSearchInput(){

        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element",
                5
        );

        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5
        );

    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button.", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present.", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button.", 5);
    }

    public void typeSearchLine(String searchLine){

        waitForElementAndSendKeys(
                By.xpath(SEARCH_INIT_ELEMENT),
                searchLine,
                "Cannot find search input",
                5
        );

    }

    public void waitForSearchResult(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        this.waitForElementPresent(
                By.xpath(searchResultXpath),
                "Cannot find search result with subString " + subString,
                5
        );


    }

    public void clickByArticleWithSubstring(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        this.waitForElementAndClick(
                By.xpath(searchResultXpath),
                "Cannot find and click search result with subString " + subString,
                5
        );


    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                By.id(SEARCH_RESULT_LIST),
                "Nothing found when searching for the word",
                10
        );

        return this.getAmountOfElements(By.id(SEARCH_RESULT_ELEMENT));

    }

    public void waitForEmptyResultsLabel(){

        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element.",
                5);


    }

    public void assertThereIsNoResultOfSearch(){

        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not to find any results."
        );

    }


}
