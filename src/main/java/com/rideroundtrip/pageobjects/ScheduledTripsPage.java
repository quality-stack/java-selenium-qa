package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the Scheduled Trips landing page and its primary navigation actions.
 */
public class ScheduledTripsPage extends BasePage
{
    @FindBy(xpath = "//h2[@class='empty-state-heading']")
    private WebElement defaultTxt;

    @FindBy(xpath = "//a[contains(text(),'Scheduled')]")
    private WebElement scheduledtab;

    @FindBy(xpath = "//a[contains(text(),'Completed')]")
    private WebElement completedtab;

    @FindBy(xpath = "//a[contains(text(),'Canceled')]")
    private WebElement canceledtab;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/a[1]")
    private WebElement booktripbtn;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/h2[1]/div[1]/input[1]")
    private WebElement calwidget;

    @FindBy(linkText = "Patients")
    private WebElement patientstab;

    /**
     * Binds the page object to the active browser session.
     */
    public ScheduledTripsPage(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Opens the patient tab from the scheduled-trips navigation.
     */
    public void openPatientsTab()
    {
        click(patientstab, "patients tab");
    }

    /**
     * Starts the booking flow from the scheduled-trips page.
     */
    public void openBookTrip()
    {
        click(booktripbtn, "book trip button");
    }
}
