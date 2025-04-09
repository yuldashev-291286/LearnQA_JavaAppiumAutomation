package com.example.javaappiumautomation.lib;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

import junit.framework.TestCase;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    protected String platform = System.getenv("PLATFORM");

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesPlatformEny();
        this.getDriver(capabilities);
        this.rotateScreenPortrait();

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

    private DesiredCapabilities getCapabilitiesPlatformEny() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","emulator-5554");
            capabilities.setCapability("platformVersion","8");
            capabilities.setCapability("automationName","Appium");
            capabilities.setCapability("appPackage","org.wikipedia");
            capabilities.setCapability("appActivity",".main.MainActivity");
            capabilities.setCapability("app","C://Git//LearnQA_AutomationMobileApplications//src//main//apks//org.wikipedia.apk");

        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName","iOS");
            capabilities.setCapability("deviceName","iPhone SE");
            capabilities.setCapability("platformVersion","11.3");
            capabilities.setCapability("app","C://Git//LearnQA_AutomationMobileApplications//src//main//apks//Wikipedia.app");

        }else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);

        }

        return capabilities;
    }

    private AppiumDriver getDriver(DesiredCapabilities capabilities) throws MalformedURLException {

        if (platform.equals(PLATFORM_ANDROID)){
            driver = new AndroidDriver(new URL(AppiumURL), capabilities);

        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(new URL(AppiumURL), capabilities);

        }
        return driver;
    }


}
