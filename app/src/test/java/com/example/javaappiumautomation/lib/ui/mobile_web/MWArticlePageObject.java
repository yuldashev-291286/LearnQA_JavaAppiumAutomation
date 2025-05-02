package com.example.javaappiumautomation.lib.ui.mobile_web;

import com.example.javaappiumautomation.lib.ui.ArticlePageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://span[contains(text(),'Java (programming language)')]";
        FOOTER_ELEMENT = "xpath://div[@class='post-content footer-content']";
        OPTIONS_BUTTON = "xpath:";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath:";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath:";
        ADD_TO_MY_LIST_OVERLAY = "xpath:";
        MY_LIST_NAME_INPUT = "xpath:";
        MY_LIST_OK_BUTTON = "xpath:";
        CLOSE_ARTICLE_BUTTON = "xpath:";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

}
