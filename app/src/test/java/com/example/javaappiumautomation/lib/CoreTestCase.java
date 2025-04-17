package com.example.javaappiumautomation.lib;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

import com.example.javaappiumautomation.lib.ui.WelcomePageObject;

import junit.framework.TestCase;

import io.appium.java_client.AppiumDriver;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();

    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait(){
        driver.rotate(PORTRAIT);
    }

    protected void rotateScreenLANDSCAPE(){
        driver.rotate(LANDSCAPE);
    }

    protected void backgroundApp(int seconds){
        driver.runAppInBackground(seconds);

    }

    private void skipWelcomePageForIOSApp(){

        if (Platform.getInstance().isIOS()){
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();

        }

    }


}
