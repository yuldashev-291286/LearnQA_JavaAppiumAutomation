package com.example.javaappiumautomation.lib;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_UPL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception {

        URL URL = new URL(APPIUM_UPL);
        if (this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());

        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());

        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());

        }

    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformVersion","8");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C://Git//LearnQA_AutomationMobileApplications//src//main//apks//org.wikipedia.apk");

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.3");
        capabilities.setCapability("app","C://Git//LearnQA_AutomationMobileApplications//src//main//apks//Wikipedia.app");

        return capabilities;
    }

    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar(){
        return System.getenv("PLATFORM");
    }



}
