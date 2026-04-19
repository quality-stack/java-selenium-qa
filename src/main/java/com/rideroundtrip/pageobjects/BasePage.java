package com.rideroundtrip.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rideroundtrip.generic.FrameworkConfig;

public abstract class BasePage
{
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger;

    protected BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, FrameworkConfig.getInstance().getLong("timeouts.explicit.seconds", 15));
        this.logger = LoggerFactory.getLogger(getClass());
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element)
    {
        click(element, "element");
    }

    protected void click(WebElement element, String elementName)
    {
        waitForClickable(element).click();
        logger.debug("Clicked {}", elementName);
    }

    protected void type(WebElement element, String value)
    {
        type(element, value, "element");
    }

    protected void type(WebElement element, String value, String elementName)
    {
        WebElement visibleElement = waitForVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(value);
        logger.debug("Typed into {}", elementName);
    }

    protected String textOf(WebElement element, String elementName)
    {
        String text = waitForVisible(element).getText().trim();
        logger.debug("Read text from {}", elementName);
        return text;
    }

    protected boolean isDisplayed(WebElement element)
    {
        return isDisplayed(element, "element");
    }

    protected boolean isDisplayed(WebElement element, String elementName)
    {
        try {
            waitForVisible(element);
            logger.debug("{} is visible", elementName);
            return true;
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException exception) {
            logger.debug("{} is not visible: {}", elementName, exception.getMessage());
            return false;
        }
    }

    protected void waitForTitle(String title)
    {
        wait.until(ExpectedConditions.titleIs(title));
        logger.debug("Page title matched {}", title);
    }

    protected void pause(long milliseconds)
    {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for page update", exception);
        }
    }

    protected WebElement waitForVisible(WebElement element)
    {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickable(WebElement element)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
