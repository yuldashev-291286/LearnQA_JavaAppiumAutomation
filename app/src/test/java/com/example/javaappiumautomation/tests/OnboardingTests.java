package com.example.javaappiumautomation.tests;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.MainPageObject;
import com.example.javaappiumautomation.lib.ui.OnboardingPageObject;
import com.example.javaappiumautomation.lib.ui.SearchPageObject;
import com.example.javaappiumautomation.lib.ui.factories.OnboardingPageObjectFactory;
import com.example.javaappiumautomation.lib.ui.factories.SearchPageObjectFactory;

import org.junit.Assert;
import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;

@Epic("Onboarding")
@Feature("Test for working with onboarding")
public class OnboardingTests extends CoreTestCase {

    private OnboardingPageObject onboardingPageObject;
    private SearchPageObject searchPageObject;

    public void setUp() throws Exception {
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
    @Description(value = "Onboarding Test")
    public void testSaveTwoArticlesOrOnboarding() throws InterruptedException {

        if (Platform.getInstance().isMW()){
            return;

        }

        // При перелистывании экрана проверяет ожидаемый Title.
        String actualTitle = onboardingPageObject.actualTitle();
        Assert.assertEquals("The Free Encyclopedia\n…in over 300 languages", actualTitle);

        // Свайпами перелистывает экраны Onboarding.
        onboardingPageObject.swipeElementToLeft();

        Thread.sleep(1000);
        System.out.println(actualTitle);

        // При перелистывании экрана проверяет ожидаемый Title.
        actualTitle = onboardingPageObject.actualTitle();
        Assert.assertEquals("New ways to explore", actualTitle);

        Thread.sleep(1000);
        System.out.println(actualTitle);

        // Свайпами перелистывает экраны Onboarding.
        onboardingPageObject.swipeElementToLeft();

        // При перелистывании экрана проверяет ожидаемый Title.
        actualTitle = onboardingPageObject.actualTitle();
        Assert.assertEquals("Reading lists with sync", actualTitle);

        Thread.sleep(1000);
        System.out.println(actualTitle);

        // Свайпами перелистывает экраны Onboarding.
        onboardingPageObject.swipeElementToLeft();

        // При перелистывании экрана проверяет ожидаемый Title.
        actualTitle = onboardingPageObject.actualTitle();
        Assert.assertEquals("Send anonymous data", actualTitle);

        Thread.sleep(1000);
        System.out.println(actualTitle);

        onboardingPageObject.waitAndClickAcceptButton();

        Thread.sleep(1000);

        // Убеждаемся в отображении Главного экрана.
        searchPageObject.initSearchInput();

    }


}
