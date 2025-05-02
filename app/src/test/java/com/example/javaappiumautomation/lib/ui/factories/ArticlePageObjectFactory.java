package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidArticlePageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSArticlePageObject;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWArticlePageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);

        } else if (Platform.getInstance().isIOS()) {
            return new iOSArticlePageObject(driver);

        } else {
            return new MWArticlePageObject(driver);
        }

    }

}
