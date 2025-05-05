package com.example.javaappiumautomation.lib.ui.ios;

import com.example.javaappiumautomation.lib.ui.WelcomePageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSWelcomePageObject extends WelcomePageObject {

    static {
        STEP_LEARN_MORE_LINK = "xpath";
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "xpath";
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "xpath";
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath";
        NEXT_LINK = "xpath";
        GET_STARTED_BUTTON = "xpath";
        SKIP = "xpath";

    }

    public iOSWelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }


}
