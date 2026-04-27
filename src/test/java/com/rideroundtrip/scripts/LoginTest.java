package com.rideroundtrip.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rideroundtrip.features.LoginFeature;
import com.rideroundtrip.generic.TestDataFactory;
import com.rideroundtrip.generic.ValidationOutcome;
import com.rideroundtrip.generic.baseLibrary;

/**
 * Exercises valid and invalid login scenarios through the shared login feature.
 */
public class LoginTest extends baseLibrary
{
    /**
     * Supplies the login test inputs from the shared test-data factory.
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData()
    {
        return TestDataFactory.loginData();
    }

    /**
     * Verifies that login behaves correctly for the provided credentials and expected outcome.
     */
    @Test(dataProvider = "loginData")
    public void loginScenarios(String username, String password, ValidationOutcome outcome)
    {
        if (ValidationOutcome.VALID == outcome) {
            requireConfig("app.url", "app.username", "app.password", "app.expectedTitle");
        } else {
            requireConfig("app.url", "invalid.username", "invalid.password");
        }

        LoginFeature loginFeature = new LoginFeature(driver);
        loginFeature.login(username, password);
        loginFeature.verifyLogin(outcome);
    }
}
