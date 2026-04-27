package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.generic.FrameworkConfig;
import com.rideroundtrip.generic.ValidationOutcome;
import com.rideroundtrip.pageobjects.LoginPage;

/**
 * Encapsulates the login business flow and its outcome assertions.
 */
public class LoginFeature
{
    /** Emits login-flow progress and assertion context. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFeature.class);

    /** Driver used to read browser state after login actions complete. */
    private final WebDriver driver;
    /** Page object used to interact with the login form. */
    private final LoginPage loginpage;

    /**
     * Creates the feature layer for login interactions.
     */
    public LoginFeature(WebDriver driver)
    {
        this.driver = driver;
        this.loginpage = new LoginPage(driver);
    }

    /**
     * Executes the login action sequence with the supplied credentials.
     */
    public void login(String username, String password)
    {
        LOGGER.info("Executing login flow for configured user type");
        loginpage.login(username, password);
    }

    /**
     * Verifies the login result against the expected scenario outcome.
     */
    public void verifyLogin(ValidationOutcome outcome)
    {
        if (ValidationOutcome.VALID == outcome) {
            String expectedTitle = FrameworkConfig.getInstance()
                    .resolve("app.expectedTitle", "", "Scheduled Trips | RoundTrip");
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Unexpected page title after valid login");
            LOGGER.info("Valid login verified with title {}", actualTitle);
            Reporter.log("valid login verified", true);
            return;
        }

        Assert.assertTrue(loginpage.isInvalidMessageDisplayed(), "Expected invalid login message to be displayed");
        LOGGER.info("Invalid login verified");
        Reporter.log("invalid login verified", true);
    }
}
