package com.example.javaappiumautomation.lib.ui.ios;

import com.example.javaappiumautomation.lib.ui.ArticlePageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://android.webkit.WebView[@text='Java (programming language)']";
        FOOTER_ELEMENT = "id:pcs-footer-container-legal";
        OPTIONS_BUTTON = "xpath://*[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public iOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }



}
