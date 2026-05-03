package com.rideroundtrip.generic;

import com.rideroundtrip.features.LoginFeature;

/**
 * Shared base class for tests that require a successful login before performing assertions.
 */
public abstract class AuthenticatedTest extends baseLibrary
{
    /**
     * Signs in with the configured valid user and verifies the expected landing page.
     */
    protected void loginWithConfiguredUser()
    {
        requireConfig("app.url", "app.username", "app.password", "app.expectedTitle");
        LoginFeature loginFeature = new LoginFeature(driver);
        loginFeature.login(
                FrameworkConfig.getInstance().get("app.username"),
                FrameworkConfig.getInstance().get("app.password"));
        loginFeature.verifyLogin(ValidationOutcome.VALID);
    }
}
