package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the patient selection screen used during trip booking.
 */
public class SelectPatientPage extends BasePage
{
    @FindBy(xpath = "//input[@id='patient-search']")
    private WebElement searchTextBox;

    @FindBy(linkText = "Add Patient")
    private WebElement addPatientBtn;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[3]")
    private WebElement searchResult;

    @FindBy(xpath = "//p[@class='empty-state-message']")
    private WebElement emptyresultmsg;

    /**
     * Binds the page object to the active browser session.
     */
    public SelectPatientPage(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Enters a patient name into the search box.
     */
    public void searchForPatient(String patientName)
    {
        type(searchTextBox, patientName, "patient search text box");
    }

    /**
     * Returns whether a patient result tile is visible.
     */
    public boolean hasSearchResult()
    {
        return isDisplayed(searchResult, "patient search result");
    }

    /**
     * Opens the first displayed patient search result.
     */
    public void openSearchResult()
    {
        click(searchResult, "patient search result");
    }

    /**
     * Returns whether the empty-result message is visible.
     */
    public boolean hasEmptyResultMessage()
    {
        return isDisplayed(emptyresultmsg, "empty patient search message");
    }
}
