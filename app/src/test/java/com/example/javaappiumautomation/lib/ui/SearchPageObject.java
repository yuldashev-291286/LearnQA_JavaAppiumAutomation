package com.example.javaappiumautomation.lib.ui;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            //SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{SUBSTRING}']",
            SEARCH_RESULT_BY_CONTAINS_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'{SUBSTRING}')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_IS_EMPTY = "org.wikipedia:id/search_container",
            SEARCH_RESULT_LIST = "org.wikipedia:id/search_results_list",
            SEARCH_RESULT_ELEMENT = "org.wikipedia:id/page_list_item_title",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']",
            SELECT_ITEM_LIST = "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='Object-oriented programming language']",
            NON_EXISTENT_ELEMENT = "org.wikipedia:id/page_title";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }


    // TEMPLATES METHODS
    private static String getResultSearchElement(String subString){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    private static String getResultContainsSearchElement(String subString){
        return SEARCH_RESULT_BY_CONTAINS_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
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

    public void waitAndClickOnSelectedItemInList(){

        this.waitForElementPresent(
                By.xpath(SELECT_ITEM_LIST),
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        this.waitForElementAndClick(
                By.xpath(SELECT_ITEM_LIST),
                "Cannot find 'Object-oriented programming language' text",
                5

        );

    }

    public void checkThatElementHasNotYetLoadedOntoPage(){

        this.assertElementPresent(
                // Передаю локатор несуществующего элемента, чтобы проверить кейс,
                // когда элемент не успел загрузиться на страницу
                By.id(NON_EXISTENT_ELEMENT),
                "Element not found on page."
        );

    }

    public void checkSearchResult(){

        this.waitForElementPresent(
                By.id(SEARCH_RESULT_LIST),
                "Nothing found when searching for the word",
                5
        );

    }

    // Убеждаемся, что в каждом результате поиска есть найденное слово.
    public void makeSureThatEachSearchResultContainsFoundWord(String subString){

        String searchResultXpath = getResultContainsSearchElement(subString);

        List<WebElement> webElementList =
                driver.findElements(By.xpath(searchResultXpath));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word");
        }

    }

    // Убеждается, что найдено несколько статей.
    public void makesSureMultipleArticlesAreFound(){

        List<WebElement> webElementList =
                driver.findElements(By.id(SEARCH_RESULT_ELEMENT));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word");
        }

    }

    // Убеждается, что результат поиска пропал.
    public void makesSureSearchResultIsGone(){

        this.waitForElementPresent(
                By.id(SEARCH_RESULT_IS_EMPTY),
                "Cannot find Search Wikipedia input",
                5
        );

    }

    // Проверка текста в поле поиска Википедии.
    public String checkTextInSearchFieldInWikipedia(){

        String textInFieldSearchWikipedia = this.assertElementHasText(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Search Wikipedia",
                "Unable to find text in the 'Wikipedia Search' box"
        );
        return textInFieldSearchWikipedia;

    }


}
