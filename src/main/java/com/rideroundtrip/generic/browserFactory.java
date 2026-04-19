package com.rideroundtrip.generic;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class browserFactory
{
    private browserFactory()
    {
    }

    public static WebDriver openBrowser(String browser, String session)
    {
        FrameworkConfig config = FrameworkConfig.getInstance();
        String normalizedBrowser = browser == null ? "chrome" : browser.trim().toLowerCase();
        String normalizedSession = session == null ? "normal" : session.trim().toLowerCase();

        WebDriver driver;

        switch (normalizedBrowser) {
            case "edge":
                configureDriverBinary("webdriver.edge.driver", "driver.edge.path", "edge");
                driver = new EdgeDriver();
                Reporter.log("Edge launched", true);
                break;
            case "firefox":
                configureDriverBinary("webdriver.gecko.driver", "driver.firefox.path", "firefox");
                driver = new FirefoxDriver();
                Reporter.log("Firefox launched", true);
                break;
            case "ie":
                configureDriverBinary("webdriver.ie.driver", "driver.ie.path", "ie");
                driver = new InternetExplorerDriver();
                Reporter.log("Internet Explorer launched", true);
                break;
            case "chrome":
            default:
                configureDriverBinary("webdriver.chrome.driver", "driver.chrome.path", "chrome");
                ChromeOptions options = new ChromeOptions();
                if ("incognito".equals(normalizedSession)) {
                    options.addArguments("--incognito");
                }
                if ("headless".equals(normalizedSession) || "true".equalsIgnoreCase(config.get("browser.headless"))) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                Reporter.log("Chrome launched", true);
                break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(config.getLong("timeouts.pageLoad.seconds", 20), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(config.getLong("timeouts.implicit.seconds", 30), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(config.getLong("timeouts.script.seconds", 60), TimeUnit.SECONDS);
        return driver;
    }

    private static void configureDriverBinary(String systemPropertyName, String configKey, String browser)
    {
        String configuredDriverPath = FrameworkConfig.getInstance().get(configKey);
        if (!configuredDriverPath.isEmpty()) {
            System.setProperty(systemPropertyName, configuredDriverPath);
            Reporter.log("Using configured driver path for " + browser, true);
            return;
        }

        if (FrameworkConfig.getInstance().getBoolean("driver.auto.manage", true)) {
            setupManagedDriver(browser);
        }
    }

    private static void setupManagedDriver(String browser)
    {
        switch (browser) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                Reporter.log("Resolved EdgeDriver automatically", true);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                Reporter.log("Resolved GeckoDriver automatically", true);
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                Reporter.log("Resolved IEDriver automatically", true);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                Reporter.log("Resolved ChromeDriver automatically", true);
                break;
        }
    }
}

