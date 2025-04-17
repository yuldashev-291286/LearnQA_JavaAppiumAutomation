package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.ArticlePageObject;
import com.example.javaappiumautomation.lib.ui.android.AndroidArticlePageObject;
import com.example.javaappiumautomation.lib.ui.ios.iOSArticlePageObject;

import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);

        } else {
            return new iOSArticlePageObject(driver);

        }

    }

}
