package com.example.javaappiumautomation.lib.ui;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import io.appium.java_client.AppiumDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_CONTAINS_SUBSTRING_TPL,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_IS_EMPTY,
            SEARCH_RESULT_LIST,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SELECT_ITEM_LIST,
            NON_EXISTENT_ELEMENT,
            SELECT_ITEM_WITH_TITLE_SUBSTRING_TPL,
            SELECT_ITEM_WITH_DESCRIPTION_SUBSTRING_TPL;


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

    // Подобрать локатор, который находит результат поиска одновременно
    // по заголовку и описанию (если заголовок или описание отличается - элемент не находится).
    // Добавить соответствующий метод в секцию TEMPLATES METHODS класса SearchPageObject.
    private static String getResultWithTitle(String subString){
        return SELECT_ITEM_WITH_TITLE_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    // Подобрать локатор, который находит результат поиска одновременно
    // по заголовку и описанию (если заголовок или описание отличается - элемент не находится).
    // Добавить соответствующий метод в секцию TEMPLATES METHODS класса SearchPageObject.
    private static String getResultWithDescription(String subString){
        return SELECT_ITEM_WITH_DESCRIPTION_SUBSTRING_TPL.replace("{SUBSTRING}", subString);
    }

    // TEMPLATES METHODS

    // В этот же класс добавить метод waitForElementByTitleAndDescription(String title, String description).
    // Он должен дожидаться результата поиска по двум строкам - по заголовку и описанию.
    // Если такой элемент не появляется, тест должен упасть с читаемой и понятной ошибкой.
    public void waitForElementByTitleAndDescription(String title, String description){

        String searchResultTitleXpath = getResultWithTitle(title);
        String searchResultDescriptionXpath = getResultWithDescription(description);

        this.waitForElementPresent(
                searchResultTitleXpath,
                "Cannot find search result with title: " + title,
                5
        );

        this.waitForElementPresent(
                searchResultDescriptionXpath,
                "Cannot find search result with description: " + description,
                5
        );


    }

    public void initSearchInput(){

        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element",
                5
        );

        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5
        );

    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button.", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present.", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button.", 5);
    }

    public void typeSearchLine(String searchLine){

        waitForElementAndSendKeys(
                SEARCH_INIT_ELEMENT,
                searchLine,
                "Cannot find search input",
                5
        );

    }

    public void waitForSearchResult(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        this.waitForElementPresent(
                searchResultXpath,
                "Cannot find search result with subString " + subString,
                5
        );

    }

    public void clickByArticleWithSubstring(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        this.waitForElementAndClick(
                searchResultXpath,
                "Cannot find and click search result with subString " + subString,
                5
        );

    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                SEARCH_RESULT_LIST,
                "Nothing found when searching for the word",
                10
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLabel(){

        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element.",
                5);

    }

    public void assertThereIsNoResultOfSearch(){

        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results."
        );

    }

    public void waitAndClickOnSelectedItemInList(){

        this.waitForElementPresent(
                SELECT_ITEM_LIST,
                "Cannot find 'Object-oriented programming language' text",
                5
        );

        this.waitForElementAndClick(
                SELECT_ITEM_LIST,
                "Cannot find 'Object-oriented programming language' text",
                5

        );

    }

    public void checkThatElementHasNotYetLoadedOntoPage(){

        this.assertElementPresent(
                // Передаю локатор несуществующего элемента, чтобы проверить кейс,
                // когда элемент не успел загрузиться на страницу
                NON_EXISTENT_ELEMENT,
                "Element not found on page."
        );

    }

    public void checkSearchResult(){

        this.waitForElementPresent(
                SEARCH_RESULT_LIST,
                "Nothing found when searching for the word",
                5
        );

    }

    // Убеждаемся, что в каждом результате поиска есть найденное слово.
    public void makeSureThatEachSearchResultContainsFoundWord(String subString){

        //String searchResultXpath = getResultContainsSearchElement(subString);

        List<WebElement> webElementList =
                driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'Java')]"));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word.");
        }

    }

    // Убеждаемся, что найдено несколько статей.
    public void makesSureMultipleArticlesAreFound(){

        List<WebElement> webElementList =
                driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        webElementList.addAll(webElementList);

        if (webElementList.isEmpty()) {
            Assert.fail("Nothing found when searching for the word.");
        }

    }

    // Убеждаемся, что найдено не менее трех статей.
    public void checkThatAtLeastThreeArticlesWereFound(){

        List<WebElement> webElementList =
                driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        webElementList.addAll(webElementList);

        if (webElementList.size() < 3) {
            Assert.fail("Less than three articles found.");
        }

    }


    // Убеждается, что результат поиска пропал.
    public void makesSureSearchResultIsGone(){

        this.waitForElementPresent(
                SEARCH_RESULT_IS_EMPTY,
                "Cannot find Search Wikipedia input",
                5
        );

    }

    // Проверка текста в поле поиска Википедии.
    public String checkTextInSearchFieldInWikipedia(){

        String textInFieldSearchWikipedia = this.assertElementHasText(
                SEARCH_INIT_ELEMENT,
                "Search Wikipedia",
                "Unable to find text in the 'Wikipedia Search' box"
        );
        return textInFieldSearchWikipedia;

    }


}
