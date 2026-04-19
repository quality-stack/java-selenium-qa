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

public class baseLibrary
{
    private static final Logger LOGGER = LoggerFactory.getLogger(baseLibrary.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>();

    protected WebDriver driver;

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

    public static WebDriver getDriver()
    {
        return DRIVER.get();
    }

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
