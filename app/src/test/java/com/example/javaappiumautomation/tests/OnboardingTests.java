package com.example.javaappiumautomation.tests;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.ui.MainPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.factories.OnboardingPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.SearchPageObjectFactory;

import org.junit.Test;

public class OnboardingTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        onboardingPageObject = OnboardingPageObjectFactory.get(driver);
        searchPageObject =  SearchPageObjectFactory.get(driver);
    }


    // Занятие 4. ДЗ 1. Ex5: Тест: Сохранение двух статей либо Онбординг.
    // Написать тест, который:
    // 1. Свайпами перелистывает экраны Onboarding.
    // 2. При перелистывании экрана проверяет ожидаемый Title.
    // 3. Нажимает кнопку: "Accept" на Android и “Get started” на iOS в конце Onboarding.
    // 4. Убеждается в отображении Главного экрана.
    @Test
    public void testSaveTwoArticlesOrOnboarding() throws InterruptedException {

        onboardingPageObject.onboardingHomePage();

        // При перелистывании экрана проверяет ожидаемый Title.
        String actualTitle = onboardingPageObject.actualTitle();
        assertEquals("The Free Encyclopedia\n…in over 300 languages", actualTitle);

        // Свайпами перелистывает экраны Onboarding.
        onboardingPageObject.swipeElementToLeft();

        Thread.sleep(1000);
        System.out.println(actualTitle);

        // При перелистывании экрана проверяет ожидаемый Title.
        actualTitle = onboardingPageObject.actualTitle();
        assertEquals("New ways to explore", actualTitle);

        Thread.sleep(1000);
        System.out.println(actualTitle);

        // Свайпами перелистывает экраны Onboarding.
        onboardingPageObject.swipeElementToLeft();

        // При перелистывании экрана проверяет ожидаемый Title.
        actualTitle = onboardingPageObject.actualTitle();
        assertEquals("Reading lists with sync", actualTitle);

        Thread.sleep(1000);
        System.out.println(actualTitle);

        // Свайпами перелистывает экраны Onboarding.
        onboardingPageObject.swipeElementToLeft();

        // При перелистывании экрана проверяет ожидаемый Title.
        actualTitle = onboardingPageObject.actualTitle();
        assertEquals("Send anonymous data", actualTitle);

        Thread.sleep(1000);
        System.out.println(actualTitle);

        onboardingPageObject.waitAndClickAcceptButton();

        Thread.sleep(1000);

        // Убеждаемся в отображении Главного экрана.
        searchPageObject.initSearchInput();

    }


}
