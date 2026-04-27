package com.rideroundtrip.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Creates and tears down a browser session around each TestNG test method.
 */
public class baseLibrary
{
    /** Writes framework lifecycle events to the configured logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(baseLibrary.class);
    /** Keeps the active driver isolated per test thread. */
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>();

    /** Stores the driver instance used by the current test method. */
    protected WebDriver driver;

    /**
     * Opens the configured browser and navigates to the requested application URL.
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browsername", "type", "url"})
    public void preconditon(
            @Optional("") String browsername,
            @Optional("") String type,
            @Optional("") String url)
    {
        FrameworkConfig config = FrameworkConfig.getInstance();
        String browser = config.resolve("browser", browsername, "chrome");
        String session = config.resolve("session.type", type, "normal");
        String appUrl = config.resolve("app.url", url, "");

        LOGGER.info("Opening browser={} session={}", browser, session);
        driver = browserFactory.openBrowser(browser, session);
        DRIVER.set(driver);

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        if (!appUrl.isEmpty()) {
            LOGGER.info("Navigating to {}", appUrl);
            driver.get(appUrl);
        }
    }

    /**
     * Closes the browser after each test method and clears thread-local state.
     */
    @AfterMethod(alwaysRun = true)
    public void postcondition()
    {
        WebDriver currentDriver = DRIVER.get();
        try {
            if (currentDriver != null) {
                LOGGER.info("Closing browser for thread");
                currentDriver.quit();
                Reporter.log("Browser closed", true);
            }
        } finally {
            DRIVER.remove();
            driver = null;
        }
    }

    /**
     * Returns the driver associated with the current thread.
     */
    public static WebDriver getDriver()
    {
        return DRIVER.get();
    }

    /**
     * Skips the test when a required configuration key has not been supplied.
     */
    protected void requireConfig(String... keys)
    {
        FrameworkConfig config = FrameworkConfig.getInstance();
        for (String key : keys) {
            if (config.get(key).trim().isEmpty()) {
                throw new SkipException("Missing required configuration: " + key);
            }
        }
    }
}
