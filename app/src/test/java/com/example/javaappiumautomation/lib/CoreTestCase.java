package com.example.javaappiumautomation.lib;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

import com.example.javaappiumautomation.lib.ui.WelcomePageObject;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class CoreTestCase /*extends TestCase*/ {

    protected RemoteWebDriver driver;

    @Before//@Override
    public void setUp() throws Exception {

        //super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();

    }

    @After//@Override
    public void tearDown() /*throws Exception*/ {

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            driver.quit();

        } else if (Platform.getInstance().isMW()) {
            driver.close();

        }

        //super.tearDown();
    }

    protected void rotateScreenPortrait(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void rotateScreenLANDSCAPE(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLANDSCAPE() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void openWikiWebPageForMobileWeb(){

        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org/wiki/");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    private void skipWelcomePageForIOSApp(){

        if (Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();

        }

    }


}
