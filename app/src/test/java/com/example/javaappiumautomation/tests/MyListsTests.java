package com.example.javaappiumautomation.tests;

import org.junit.Assert;
import org.junit.Test;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.AuthorizationPageObject;
import com.example.javaappiumautomation.lib.ui.MyListsPageObject;
import com.example.javaappiumautomation.lib.ui.NavigationUI;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.factories.ArticlePageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.AuthorizationPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.MyListsPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.NavigationUIFactory;
import com.example.javaappiumautomation.lib.ui.factories.SearchPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWAuthorizationPageObject;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;

@Epic("Featured")
@Feature("Test for working with favorites")
public class MyListsTests extends CoreTestCase {
    private AuthorizationPageObject Auth;
    private SearchPageObject searchPageObject;
    private static final String
            login = "YuldashevRuslan",
            password = "YZ]W7aq4-hRdP[]U";

    public void setUp() throws Exception {
        super.setUp();

        Auth = AuthorizationPageObjectFactory.get(driver);
        searchPageObject =  SearchPageObjectFactory.get(driver);
    }


    // Занятие 4. Учебный тест №2. Тест не работает для Android, переделан под Mobile_Web.
    //
    // Ex17: Рефакторинг. Адаптировать под MW тест на удаление одной сохраненной статьи из двух.
    // Вместо проверки title-элемента придумать другой способ верификации оставшейся статьи
    // (т.е. способ убедиться, что осталась в сохраненных ожидаемая статья).
    //
    @Test
    @Description(value = "Deleting one saved article from two")
    public void testSaveTwoArticlesToFavoritesAndDeleteOneSaveArticle() throws InterruptedException {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            return;

        }
        // Открываем главное меню
        Auth.clickMainMenu();

        Thread.sleep(1000);

        // Авторизуемся на странице Википедии
        Auth.clickAuthButton();
        Auth.enterLoginData(login, password);
        Auth.submitForm();

        // Проверяем title на авторизованной странице
        Auth.checkExpectedPageTitle();

        // Ищем на википедии статьи для сохранения
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        // Добавляем в избранное первую статью
        searchPageObject.waitByArticleWithSubstring("Java (programming language)");
        searchPageObject.addArticleToFavorites("Java (programming language)");

        // Добавляем в избранное вторую статью
        searchPageObject.waitByArticleWithSubstring("JavaScript");
        searchPageObject.addArticleToFavorites("JavaScript");

        // Возвращаемся на стартовую страницу
        searchPageObject.returnToStartPage();

        // Открываем главное меню
        Auth.clickMainMenu();

        Thread.sleep(1000);

        // Переходим в избранное
        searchPageObject.openFavorites();

        Thread.sleep(1000);

        // Проверяем, что избранное не пустое
        if (!searchPageObject.checkThatFavoritesAreNotEmpty()){
            return;
        }

        // Удаляем одну сохраненную статью
        searchPageObject.deleteSavedArticle();

        // Проверить, что осталась вторая статья, которую мы не удаляли
        if (!searchPageObject.checkRestOfArticleHref()){
            Assert.fail("Wrong article left.");

        }

    }


}
