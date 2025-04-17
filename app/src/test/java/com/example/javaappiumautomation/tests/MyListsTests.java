package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.MyListsPageObject;
import com.example.javaappiumautomation.lib.ui.NavigationUI;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.factories.ArticlePageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.MyListsPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.NavigationUIFactory;
import com.example.javaappiumautomation.lib.ui.factories.OnboardingPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;
    private static final String name_of_folder = "Learning programming";

    protected void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        searchPageObject =  SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
        navigationUI = NavigationUIFactory.get(driver);
        myListsPageObject = MyListsPageObjectFactory.get(driver);
    }


    // Занятие 4. Учебный тест №2. Тест не работает.
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        Thread.sleep(5000);

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList(name_of_folder); // Тест всегда падает на этом месте.
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);

        }

        myListsPageObject.swipeByArticleToDelete(article_title);

    }


}
