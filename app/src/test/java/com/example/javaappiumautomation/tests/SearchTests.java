package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;


public class SearchTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = new OnboardingPageObject(driver);
        searchPageObject = new SearchPageObject(driver);
    }


    @Test
    public void testSearch(){

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

    }

    @Test
    public void testCancelSearch(){

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

    }

    // Занятие 4. Учебный тест №3.
    @Test
    public void testAmountOfNotEmptySearch() throws InterruptedException {

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        Thread.sleep(5000);

        assertTrue(
                "We found too few results !",
                amount_of_search_results > 0
        );


    }

    // Занятие 4. Учебный тест №4.
    @Test
    public void testAmountOfEmptySearch() throws InterruptedException {

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        String search_line = "test45";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(5000);

        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();

    }

    // Ex4*: Тест: Проверка слов в поиске.
    // 1) Написать тест, который делает поиск по какому-то слову. Например, JAVA.
    // 2) Затем убеждается, что в каждом результате поиска есть это слово.
    @Test
    public void testCheckWordsInSearch() throws InterruptedException {

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(2000);

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

        onboardingPageObject.waitSkipAndClick();

        // 1) Ищем какое-то слово.
        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(2000);

        // 2) Убеждаемся, что найдено несколько статей.
        searchPageObject.checkSearchResult();
        searchPageObject.makesSureMultipleArticlesAreFound();

        // 3) Отменяем поиск.
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();

        // 4) Убеждаемся, что результат поиска пропал.
        searchPageObject.makesSureSearchResultIsGone();
        searchPageObject.initSearchInput();

    }

    // Ex2: Создание метода.
    // Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит текст
    // (в разных версиях приложения это могут быть тексты "Search..." или "Search Wikipedia",
    // правильным вариантом следует считать тот, которые есть сейчас).
    // Очевидно, что тест должен использовать написанный ранее метод.
    @Test
    public void testCheckForExpectedText(){

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();

        String textInFieldSearchWikipedia = searchPageObject.checkTextInSearchFieldInWikipedia();

        assertEquals("Search Wikipedia", textInFieldSearchWikipedia);

    }


}
