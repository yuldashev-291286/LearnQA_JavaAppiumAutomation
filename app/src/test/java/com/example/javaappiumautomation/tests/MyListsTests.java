package com.example.javaappiumautomation.tests;

import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.AuthorizationPageObject;
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
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;
    private NavigationUI navigationUI;
    private MyListsPageObject myListsPageObject;
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "YuldashevRuslan",
            password = "YZ]W7aq4-hRdP[]U";

    protected void setUp() throws Exception {
        super.setUp();

        searchPageObject =  SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
        navigationUI = NavigationUIFactory.get(driver);
        myListsPageObject = MyListsPageObjectFactory.get(driver);
    }


    // Занятие 4. Учебный тест №2. Тест не работает для Android, переделан под Mobile_Web.
    @Test
    public void testSaveFirstArticleToMyList() {

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            return;

        }
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        articlePageObject.addArticlesToMySaved();

        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login.",
                    article_title,
                    articlePageObject.getArticleTitle()

            );

            articlePageObject.addArticlesToMySaved();

        }

        articlePageObject.closeArticle();

        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        if (Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);

        }

        myListsPageObject.swipeByArticleToDelete(article_title);

    }


}
