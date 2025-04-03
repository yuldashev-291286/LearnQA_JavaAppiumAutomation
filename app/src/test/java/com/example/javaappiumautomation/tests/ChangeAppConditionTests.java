package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.SkipPageObject;

public class ChangeAppConditionTests extends CoreTestCase {

    private SkipPageObject skipPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        skipPageObject = new SkipPageObject(driver);
    }


    // Занятие 4. Учебный тест №5.
    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = articlePageObject.getArticleTitle();

        this.rotateScreenLANDSCAPE();

        String title_after_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );


    }

    // Занятие 4. Учебный тест №6.
    @Test
    public void testCheckSearchArticleInBackground() {

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

        this.backgroundApp(2);

        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

    }

}
