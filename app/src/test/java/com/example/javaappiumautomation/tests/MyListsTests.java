package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.MyListsPageObject;
import com.example.javaappiumautomation.lib.ui.NavigationUI;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.SkipPageObject;

public class MyListsTests extends CoreTestCase {

    private SkipPageObject skipPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        skipPageObject = new SkipPageObject(driver);
    }


    // Занятие 4. Учебный тест №2. Тест не работает.
    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {

        skipPageObject.waitSkipAndClick();

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        Thread.sleep(5000);

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);


    }


}
