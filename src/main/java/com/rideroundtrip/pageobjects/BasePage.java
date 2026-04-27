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

/**
 * Base page object that standardizes waits and low-level UI interactions.
 */
public abstract class BasePage
{
    /** Active driver used by the page object. */
    protected final WebDriver driver;
    /** Shared explicit-wait helper for stable element synchronization. */
    protected final WebDriverWait wait;
    /** Page-specific logger used by interaction helpers. */
    protected final Logger logger;

    /**
     * Wires the driver, wait helper, and PageFactory bindings for a page object.
     */
    protected BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, FrameworkConfig.getInstance().getLong("timeouts.explicit.seconds", 15));
        this.logger = LoggerFactory.getLogger(getClass());
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks an element using the default interaction label.
     */
    protected void click(WebElement element)
    {
        click(element, "element");
    }

    /**
     * Clicks an element after it becomes clickable.
     */
    protected void click(WebElement element, String elementName)
    {
        waitForClickable(element).click();
        logger.debug("Clicked {}", elementName);
    }

    /**
     * Types text into an element using the default interaction label.
     */
    protected void type(WebElement element, String value)
    {
        type(element, value, "element");
    }

    /**
     * Clears and types into an element once it becomes visible.
     */
    protected void type(WebElement element, String value, String elementName)
    {
        WebElement visibleElement = waitForVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(value);
        logger.debug("Typed into {}", elementName);
    }

    /**
     * Reads text from a visible element and trims the resulting value.
     */
    protected String textOf(WebElement element, String elementName)
    {
        String text = waitForVisible(element).getText().trim();
        logger.debug("Read text from {}", elementName);
        return text;
    }

    /**
     * Checks whether an element is currently visible using the default label.
     */
    protected boolean isDisplayed(WebElement element)
    {
        return isDisplayed(element, "element");
    }

    /**
     * Checks whether an element is visible without failing the test immediately.
     */
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

    /**
     * Waits until the browser title matches the supplied string.
     */
    protected void waitForTitle(String title)
    {
        wait.until(ExpectedConditions.titleIs(title));
        logger.debug("Page title matched {}", title);
    }

    /**
     * Pauses execution for a fixed duration when no better synchronization is available.
     */
    protected void pause(long milliseconds)
    {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for page update", exception);
        }
    }

    /**
     * Waits until an element becomes visible.
     */
    protected WebElement waitForVisible(WebElement element)
    {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until an element becomes clickable.
     */
    protected WebElement waitForClickable(WebElement element)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
