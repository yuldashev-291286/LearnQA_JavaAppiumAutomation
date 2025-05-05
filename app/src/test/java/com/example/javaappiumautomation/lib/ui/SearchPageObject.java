package com.example.javaappiumautomation.lib.ui;

import com.example.javaappiumautomation.lib.Platform;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INIT_ELEMENT_WEB,
            SEARCH_WIKIPEDIA_PLACEHOLDER,
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
            SELECT_ITEM_WITH_DESCRIPTION_SUBSTRING_TPL,
            SELECT_ITEM_LIST_WEB,
            NON_EXISTENT_ELEMENT_WEB,
            RETURN_START_PAGE,
            EXPECTED_PAGE_TITLE,
            ADD_FOUND_ARTICLE_TO_FAVORITES,
            FAVORITES_BUTTON,
            DELETE_SAVED_ARTICLE,
            FAVORITES_NOT_EMPTY,
            REST_ARTICLE,
            REST_ARTICLE_HREF;


    public SearchPageObject(RemoteWebDriver driver){
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

    // Локатор, который добавляет найденную статью на Википедии в избранное, для платформы mobile_web.
    private static String addFoundArticleToFavorites(String subString){
        return ADD_FOUND_ARTICLE_TO_FAVORITES.replace("{SUBSTRING}", subString);
    }

    // TEMPLATES METHODS

    // В этот же класс добавить метод waitForElementByTitleAndDescription(String title, String description).
    // Он должен дожидаться результата поиска по двум строкам - по заголовку и описанию.
    // Если такой элемент не появляется, тест должен упасть с читаемой и понятной ошибкой.
    public void waitForElementByTitleAndDescription(String title, String description){

        String searchResultTitleXpath = getResultWithTitle(title);
        String searchResultDescriptionXpath = getResultWithDescription(description);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

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

        } else if (Platform.getInstance().isMW()){

            this.waitForElementPresent(
                    searchResultTitleXpath,
                    "Cannot find search result with title: " + title,
                    1
            );

            this.waitForElementPresent(
                    searchResultDescriptionXpath,
                    "Cannot find search result with description: " + description,
                    1
            );


        }


    }

    public void initSearchInput(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
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

        } else if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    SEARCH_INIT_ELEMENT,
                    "Cannot find search input after clicking search init element",
                    1
            );
            this.waitForElementAndClick(
                    SEARCH_INIT_ELEMENT,
                    "Cannot find and click search init element",
                    1
            );

        }


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

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            waitForElementAndSendKeys(
                    SEARCH_INIT_ELEMENT,
                    searchLine,
                    "Cannot find search input",
                    5
            );

        } else if (Platform.getInstance().isMW()){
            waitForElementAndSendKeys(
                    SEARCH_INIT_ELEMENT_WEB,
                    searchLine,
                    "Cannot find search input",
                    5
            );

        }

    }

    public void waitForSearchResult(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            this.waitForElementPresent(
                    searchResultXpath,
                    "Cannot find search result with subString " + subString,
                    5
            );

        } else if (Platform.getInstance().isMW()){

            this.waitForElementPresent(
                    searchResultXpath,
                    "Cannot find search result with subString " + subString,
                    5
            );

        }


    }

    public void clickByArticleWithSubstring(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.waitForElementAndClick(
                    searchResultXpath,
                    "Cannot find and click search result with subString " + subString,
                    5
            );

        } else if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    searchResultXpath,
                    "Cannot find and click search result with subString " + subString,
                    5
            );

        }

    }

    public void waitByArticleWithSubstring(String subString){

        String searchResultXpath = getResultSearchElement(subString);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.waitForElementPresent(
                    searchResultXpath,
                    "Cannot find and click search result with subString " + subString,
                    5
            );

        } else if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    searchResultXpath,
                    "Cannot find and click search result with subString " + subString,
                    5
            );

        }

    }

    public void addArticleToFavorites(String subString){

        String searchResultXpath = addFoundArticleToFavorites(subString);

        if (Platform.getInstance().isMW()){
            this.waitForElementPresent(
                    searchResultXpath,
                    "Cannot find button favorites",
                    5);

            this.waitForElementAndClick(
                    searchResultXpath,
                    "Cannot find button favorites",
                    5);

        }
    }

    public void returnToStartPage(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(
                    RETURN_START_PAGE,
                    "Cannot find button for return start page",
                    5);

            this.waitForElementPresent(
                    EXPECTED_PAGE_TITLE,
                    "Cannot find expected title");

        }

    }

    public void openFavorites(){
        if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    FAVORITES_BUTTON,
                    "Cannot find favorites button",
                    5);

            this.waitForElementAndClick(
                    FAVORITES_BUTTON,
                    "Cannot find favorites button",
                    5);
        }

    }

    public boolean checkThatFavoritesAreNotEmpty(){

        boolean empty = false;
        if (Platform.getInstance().isMW()) {
            try {
                this.waitForElementPresent(
                        FAVORITES_NOT_EMPTY,
                        "There is nothing in the favorites",
                        5);
                empty = true;

            } catch (Exception e) {
                System.out.println("There are no articles in the favorites.");

            }

        }
        return empty;

    }

    public void deleteSavedArticle() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    DELETE_SAVED_ARTICLE,
                    "Cannot find favorites button",
                    5);


            this.waitForElementAndClick(
                    DELETE_SAVED_ARTICLE,
                    "Cannot find favorites button",
                    5);
        }
    }

    // Ex17: Рефакторинг. Адаптировать под MW тест на удаление одной сохраненной статьи из двух.
    // Вместо проверки title-элемента придумать другой способ верификации оставшейся статьи
    // (т.е. способ убедиться, что осталась в сохраненных ожидаемая статья).
    //
    // Здесь осташееся статья проверяется по тексту 'Java (programming language)', не по заголовку(title) элемента
    public boolean checkRestOfArticle(){

        boolean restArticle = false;
        if (Platform.getInstance().isMW()) {
            try {
                this.waitForElementPresent(
                        REST_ARTICLE,
                        "Cannot find favorites button",
                        5);

                restArticle = true;

            } catch (Exception e){
                System.out.println("Wrong article left.");

            }

        }
        return restArticle;

    }

    // Ex17: Рефакторинг. Адаптировать под MW тест на удаление одной сохраненной статьи из двух.
    // Вместо проверки title-элемента придумать другой способ верификации оставшейся статьи
    // (т.е. способ убедиться, что осталась в сохраненных ожидаемая статья).
    //
    // Здесь осташееся статья проверяется по ссылке href '/wiki/Java_(programming_language)', не по заголовку(title) элемента
    public boolean checkRestOfArticleHref(){

        boolean restArticle = false;
        if (Platform.getInstance().isMW()) {
            try {
                this.waitForElementPresent(
                        REST_ARTICLE_HREF,
                        "Cannot find favorites button",
                        5);

                restArticle = true;

            } catch (Exception e){
                System.out.println("Wrong article left.");

            }

        }
        return restArticle;

    }

    public int getAmountOfFoundArticles(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.waitForElementPresent(
                    SEARCH_RESULT_LIST,
                    "Nothing found when searching for the word",
                    10
            );

        } else if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    SEARCH_RESULT_LIST,
                    "Nothing found when searching for the word",
                    5
            );

        }

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLabel(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.waitForElementPresent(
                    SEARCH_EMPTY_RESULT_ELEMENT,
                    "Cannot find empty result element.",
                    5);

        } else if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    SEARCH_EMPTY_RESULT_ELEMENT,
                    "Cannot find empty result element.",
                    5);

        }

    }

    public void assertThereIsNoResultOfSearch(){

        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results."
        );

    }

    public void waitAndClickOnSelectedItemInList(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

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

        } else if (Platform.getInstance().isMW()){

            this.waitForElementPresent(
                    SELECT_ITEM_LIST_WEB,
                    "Cannot find 'Java (programming language)' text",
                    2
            );

            this.waitForElementAndClick(
                    SELECT_ITEM_LIST_WEB,
                    "Cannot find 'Java (programming language)' text",
                    2

            );

        }


    }

    public void checkThatElementHasNotYetLoadedOntoPage(){

        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            this.assertElementPresent(
                    // Передаю локатор несуществующего элемента, чтобы проверить кейс,
                    // когда элемент не успел загрузиться на страницу
                    NON_EXISTENT_ELEMENT,
                    "Element not found on page."
            );

        } else if (Platform.getInstance().isMW()) {

            this.assertElementPresent(
                    // Передаю локатор несуществующего элемента, чтобы проверить кейс,
                    // когда элемент не успел загрузиться на страницу
                    NON_EXISTENT_ELEMENT_WEB,
                    "Element not found on page."
            );

        }


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

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            List<WebElement> webElementList =
                    driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text,'Java')]"));
            webElementList.addAll(webElementList);

            if (webElementList.isEmpty()) {
                Assert.fail("Nothing found when searching for the word.");
            }

        } else if (Platform.getInstance().isMW()) {

            List<WebElement> webElementList =
                    driver.findElements(By.xpath("//li[contains(@title, 'Java')]"));
            webElementList.addAll(webElementList);

            if (webElementList.isEmpty()) {
                Assert.fail("Nothing found when searching for the word.");
            }

        }


    }

    // Убеждаемся, что найдено несколько статей.
    public void makesSureMultipleArticlesAreFound(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            List<WebElement> webElementList =
                    driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
            webElementList.addAll(webElementList);

            if (webElementList.isEmpty()) {
                Assert.fail("Nothing found when searching for the word.");
            }

        } else if (Platform.getInstance().isMW()) {

            List<WebElement> webElementList =
                    driver.findElements(By.xpath("//li[@title]"));
            webElementList.addAll(webElementList);

            if (webElementList.isEmpty()) {
                Assert.fail("Nothing found when searching for the word.");
            }

        }


    }

    // Убеждаемся, что найдено не менее трех статей.
    public void checkThatAtLeastThreeArticlesWereFound(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            List<WebElement> webElementList =
                    driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
            webElementList.addAll(webElementList);

            if (webElementList.size() < 3) {
                Assert.fail("Less than three articles found.");
            }

        } else if (Platform.getInstance().isMW()) {

            List<WebElement> webElementList =
                    driver.findElements(By.xpath("//li[@title]"));
            webElementList.addAll(webElementList);

            if (webElementList.size() < 3) {
                Assert.fail("Less than three articles found.");
            }

        }

    }


    // Убеждается, что результат поиска пропал.
    public void makesSureSearchResultIsGone(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.waitForElementPresent(
                    SEARCH_RESULT_IS_EMPTY,
                    "Cannot find Search Wikipedia input",
                    5
            );

        } else if (Platform.getInstance().isMW()) {
            this.waitForElementPresent(
                    SEARCH_RESULT_IS_EMPTY,
                    "Cannot find Search Wikipedia input",
                    5
            );

        }


    }

    // Проверка текста в поле поиска Википедии.
    public String checkTextInSearchFieldInWikipedia(){

        String textInFieldSearchWikipedia = null;

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){

            textInFieldSearchWikipedia = this.assertElementHasText(
                    SEARCH_INIT_ELEMENT,
                    "Search Wikipedia",
                    "Unable to find text in the 'Wikipedia Search' box"
            );

        } else if (Platform.getInstance().isMW()) {

            textInFieldSearchWikipedia = this.assertElementHasText(
                    SEARCH_WIKIPEDIA_PLACEHOLDER,
                    "Search Wikipedia",
                    "Unable to find text in the 'Wikipedia Search' box"
            );

        }
        return textInFieldSearchWikipedia;

    }


}
