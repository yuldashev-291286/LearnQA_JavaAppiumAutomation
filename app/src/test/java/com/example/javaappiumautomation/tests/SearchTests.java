package com.example.javaappiumautomation.tests;

import org.junit.Assert;
import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.factories.OnboardingPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.SearchPageObjectFactory;


public class SearchTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;

    public void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        searchPageObject =  SearchPageObjectFactory.get(driver);
    }


    @Test
    public void testSearch(){

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

    }

    @Test
    public void testCancelSearch(){

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

    }

    // Занятие 4. Учебный тест №3.
    @Test
    public void testAmountOfNotEmptySearch() throws InterruptedException {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        System.out.println(amount_of_search_results);

        Thread.sleep(1000);

        Assert.assertTrue(
                "We found too few results !",
                amount_of_search_results > 0
        );


    }

    // Занятие 4. Учебный тест №4.
    @Test
    public void testAmountOfEmptySearch() throws InterruptedException {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        String search_line = "test45";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(1000);

        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();

    }

    // Ex4*: Тест: Проверка слов в поиске.
    // 1) Написать тест, который делает поиск по какому-то слову. Например, JAVA.
    // 2) Затем убеждается, что в каждом результате поиска есть это слово.
    @Test
    public void testCheckWordsInSearch() throws InterruptedException {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(1000);

        searchPageObject.checkSearchResult();
        searchPageObject.makeSureThatEachSearchResultContainsFoundWord(search_line);

    }


    // Ex3: Тест: Отмена поиска.
    // Написать тест, который:
    // 1) Ищет какое-то слово.
    // 2) Убеждается, что найдено несколько статей.
    // 3) Отменяет поиск.
    // 4) Убеждается, что результат поиска пропал
    @Test
    public void testCheckCancelSearch() throws InterruptedException {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        // 1) Ищем какое-то слово.
        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(1000);

        // 2) Убеждаемся, что найдено несколько статей.
        searchPageObject.checkSearchResult();
        searchPageObject.makesSureMultipleArticlesAreFound();

        // 3) Отменяем поиск.
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();

        // 4) Убеждаемся, что результат поиска пропал.
        searchPageObject.makesSureSearchResultIsGone();

    }

    // Ex2: Создание метода.
    // Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит текст
    // (в разных версиях приложения это могут быть тексты "Search..." или "Search Wikipedia",
    // правильным вариантом следует считать тот, которые есть сейчас).
    // Очевидно, что тест должен использовать написанный ранее метод.
    @Test
    public void testCheckForExpectedText(){

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();

        String textInFieldSearchWikipedia = searchPageObject.checkTextInSearchFieldInWikipedia();

        Assert.assertEquals("Search Wikipedia", textInFieldSearchWikipedia);

    }

    // Написать тест, который будет делать поиск по любому запросу на ваш выбор
    // (поиск по этому слову должен возвращать как минимум 3 результата).
    // Далее тест должен убеждаться, что в результате поиска присутствуют три элемента,
    // содержащие ожидаемые вами article_title и article_description.
    // Пример проверки одного из элементов:
    // your_page.waitForElementByTitleAndDescription("title_A","article_A_description");
    @Test
    public void testSearchElementByHeadAndDescription() throws InterruptedException {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();

        // Слово, по которому делаем поиск, результат которого не менее трех статей.
        String searchLine = "Java";
        // Слово, по которому делаем поиск, результат которого менее трех статей.
        // String searchLine = "test123";

        searchPageObject.typeSearchLine(searchLine);

        Thread.sleep(1000);

        // Убеждаемся, что найдено не менее трех статей.
        searchPageObject.checkThatAtLeastThreeArticlesWereFound();

        if (Platform.getInstance().isAndroid()){

            // Первый ожидаемый результат в поиске.
            String resultOfSearchWithTitleContains = "Java (programming language)";
            String resultOfSearchWithDescriptionContains = "Object-oriented programming language";
            searchPageObject.waitForElementByTitleAndDescription(
                    resultOfSearchWithTitleContains,
                    resultOfSearchWithDescriptionContains);

            // Второй ожидаемый результат в поиске.
            resultOfSearchWithTitleContains = "JavaScript";
            resultOfSearchWithDescriptionContains = "High-level programming language";
            searchPageObject.waitForElementByTitleAndDescription(
                    resultOfSearchWithTitleContains,
                    resultOfSearchWithDescriptionContains);

            // Третий ожидаемый результат в поиске.
            resultOfSearchWithTitleContains = "Java version history";
            resultOfSearchWithDescriptionContains = "List of versions of the Java programming language";
            searchPageObject.waitForElementByTitleAndDescription(
                    resultOfSearchWithTitleContains,
                    resultOfSearchWithDescriptionContains);

        } else if (Platform.getInstance().isMW()) {

            // Первый ожидаемый результат в поиске.
            String resultOfSearchWithTitleContains = "Java (programming language)";
            String resultOfSearchWithDescriptionContains = "Object-oriented programming language";
            searchPageObject.waitForElementByTitleAndDescription(
                    resultOfSearchWithTitleContains,
                    resultOfSearchWithDescriptionContains);

            // Второй ожидаемый результат в поиске.
            resultOfSearchWithTitleContains = "JavaScript";
            resultOfSearchWithDescriptionContains = "High-level programming language";
            searchPageObject.waitForElementByTitleAndDescription(
                    resultOfSearchWithTitleContains,
                    resultOfSearchWithDescriptionContains);

            // Третий ожидаемый результат в поиске.
            resultOfSearchWithTitleContains = "Java version history";
            resultOfSearchWithDescriptionContains = "List of versions of the Java programming language";
            searchPageObject.waitForElementByTitleAndDescription(
                    resultOfSearchWithTitleContains,
                    resultOfSearchWithDescriptionContains);

        }


    }



}
