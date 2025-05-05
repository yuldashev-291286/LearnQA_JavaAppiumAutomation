package com.example.javaappiumautomation.lib.ui.factories;

import com.example.javaappiumautomation.lib.Platform;
import com.example.javaappiumautomation.lib.ui.AuthorizationPageObject;
import com.example.javaappiumautomation.lib.ui.mobile_web.MWAuthorizationPageObject;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObjectFactory {

    public static AuthorizationPageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().isMW()){
            return new MWAuthorizationPageObject(driver);

        }
        return null;
    }

}
