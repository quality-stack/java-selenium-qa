package com.rideroundtrip.generic;

import com.rideroundtrip.features.LoginFeature;

public abstract class AuthenticatedTest extends baseLibrary
{
    protected void loginWithConfiguredUser()
    {
        requireConfig("app.url", "app.username", "app.password", "app.expectedTitle");
        LoginFeature loginFeature = new LoginFeature(driver);
        loginFeature.login(
                FrameworkConfig.getInstance().get("app.username"),
                FrameworkConfig.getInstance().get("app.password"));
        loginFeature.verifylogin(1);
    }
}
