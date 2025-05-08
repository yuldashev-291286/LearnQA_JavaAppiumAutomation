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

public class ChangeAppConditionTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;
    private ArticlePageObject articlePageObject;

    public void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        searchPageObject =  SearchPageObjectFactory.get(driver);
        articlePageObject = ArticlePageObjectFactory.get(driver);
    }


    // Занятие 4. Учебный тест №5.
    @Test
    public void testChangeScreenOrientationOnSearchResults() {

        if (Platform.getInstance().isMW()){
            return;
        }
        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Java (programming language)");

        String title_before_rotation = articlePageObject.getArticleTitle();

        this.rotateScreenLANDSCAPE();

        String title_after_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation
        );


    }

    // Занятие 4. Учебный тест №6.
    @Test
    public void testCheckSearchArticleInBackground() {

        if (Platform.getInstance().isMW()){
            return;
        }
        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

        this.backgroundApp(2);

        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java (programming language)");

    }


    // Ex7*: Поворот экрана.
    //
    // Appium устроен так, что может сохранить у себя в памяти поворот экрана, который использовался в предыдущем тесте,
    // и начать новый тест с тем же поворотом. Мы написали тест на поворот экрана, и он может сломаться до того,
    // как положение экрана восстановится. Следовательно, если мы запустим несколько тестов одновременно,
    // последующие тесты будут выполняться в неправильном положении экрана, что может привести к незапланированным проблемам.
    //
    // Как нам сделать так, чтобы после теста на поворот экрана сам экран всегда оказывался в правильном положении,
    // даже если тест упал в тот момент, когда экран был наклонен?
    @Test
    public void testScreenRotation() {

        if (Platform.getInstance().isMW()){
            return;
        }
        // Для примера. Из предыдущего теста вернулась ориентация экрана LANDSCAPE.
        this.rotateScreenLANDSCAPE();
        //System.out.println(driver.getOrientation());

        // В начале теста ставим положение экрана в портретный режим.
        this.rotateScreenPortrait();
        //mainPageObject.returnScreenOrientationPortrait(driver.getOrientation());
        //System.out.println(driver.getOrientation());

        onboardingPageObject.waitSkipAndClick();

        searchPageObject.initSearchInput();

    }


}
