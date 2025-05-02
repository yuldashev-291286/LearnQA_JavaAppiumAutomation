package com.example.javaappiumautomation.tests;

import com.example.javaappiumautomation.lib.CoreTestCase;
import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.WelcomePageObject;

import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome(){

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

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
