package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.SkipPageObject;

public class SearchTests extends CoreTestCase {

    private SkipPageObject skipPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        skipPageObject = new SkipPageObject(driver);
    }


    @Test
    public void testSearch(){

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

    }

    @Test
    public void testCancelSearch(){

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();

    }

    // Занятие 4. Учебный тест №3.
    @Test
    public void testAmountOfNotEmptySearch() throws InterruptedException {

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
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

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String search_line = "test45";
        searchPageObject.typeSearchLine(search_line);

        Thread.sleep(5000);

        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();

    }



}
