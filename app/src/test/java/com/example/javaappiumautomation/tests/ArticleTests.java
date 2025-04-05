package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;

public class ArticleTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    protected void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = new OnboardingPageObject(driver);
        searchPageObject = new SearchPageObject(driver);
        articlePageObject = new ArticlePageObject(driver);
    }


    @Test
    public void testCompareArticleTitle() {

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

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

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        Thread.sleep(5000);

        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }

    // Ex6: Тест: assert title
    // Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
    // Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
    // Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    @Test
    public void testOpenArticleAndMakeSureThatSheHasTitleElement() {

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.waitAndClickOnSelectedItemInList();

        searchPageObject.checkThatElementHasNotYetLoadedOntoPage();

    }


}
