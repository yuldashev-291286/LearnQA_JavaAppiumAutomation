package com.example.javaappiumautomation.tests;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.WelcomePageObject;
import com.example.javaappiumautomation.lib.ui.factories.WelcomePageObjectFactory;

import org.junit.Test;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;

@Epic("Home page")
@Feature("Test for working with the start page")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Description(value = "Walking through the welcome screen")
    public void testPassThroughWelcome(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }
        WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);

        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWayToExplorerText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditPreferredLangText();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreAboutDataCollectedText();
        welcomePageObject.clickGetStartedButton();


    }


}
