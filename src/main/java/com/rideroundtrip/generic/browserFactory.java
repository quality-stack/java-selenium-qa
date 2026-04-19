package com.rideroundtrip.generic;

import java.util.concurrent.TimeUnit;

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
                setDriverExecutableIfConfigured("webdriver.edge.driver", "driver.edge.path");
                driver = new EdgeDriver();
                Reporter.log("Edge launched", true);
                break;
            case "firefox":
                setDriverExecutableIfConfigured("webdriver.gecko.driver", "driver.firefox.path");
                driver = new FirefoxDriver();
                Reporter.log("Firefox launched", true);
                break;
            case "ie":
                setDriverExecutableIfConfigured("webdriver.ie.driver", "driver.ie.path");
                driver = new InternetExplorerDriver();
                Reporter.log("Internet Explorer launched", true);
                break;
            case "chrome":
            default:
                setDriverExecutableIfConfigured("webdriver.chrome.driver", "driver.chrome.path");
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

    private static void setDriverExecutableIfConfigured(String systemPropertyName, String configKey)
    {
        String configuredDriverPath = FrameworkConfig.getInstance().get(configKey);
        if (!configuredDriverPath.isEmpty()) {
            System.setProperty(systemPropertyName, configuredDriverPath);
        }
    }
}

