package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.SkipPageObject;

public class ArticleTests extends CoreTestCase {

    private SkipPageObject skipPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        skipPageObject = new SkipPageObject(driver);
    }


    @Test
    public void testCompareArticleTitle() {

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    // Занятие 4. Учебный тест №1. Swipe.
    @Test
    public void testSwipeArticle() throws InterruptedException {

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        Thread.sleep(5000);

        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }


}
