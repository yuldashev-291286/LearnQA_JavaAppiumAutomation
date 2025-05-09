package com.example.javaappiumautomation.tests;

import org.junit.Assert;
import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.factories.ArticlePageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.OnboardingPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.SearchPageObjectFactory;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;

@Epic("Wikipedia articles")
@Feature("Tests for working with Wikipedia articles")
public class ArticleTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    public void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        searchPageObject = SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
    }


    @Test
    @Description(value = "Comparison of article titles")
    public void testCompareArticleTitle() {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );

    }

    // Занятие 4. Учебный тест №1. Swipe.
    @Test
    @Description(value = "Swipe on article")
    public void testSwipeArticle() throws InterruptedException {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        Thread.sleep(2000);

        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }

    // Ex6: Тест: assert title
    // Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
    // Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
    // Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    @Test
    @Description(value = "A test that opens an article and makes sure it has a title element")
    public void testOpenArticleAndMakeSureThatSheHasTitleElement() {

        if (Platform.getInstance().isAndroid()){
            onboardingPageObject.waitSkipAndClick();
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.waitAndClickOnSelectedItemInList();

        searchPageObject.checkThatElementHasNotYetLoadedOntoPage();

    }


}
