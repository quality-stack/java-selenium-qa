package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public SelectPatientPage(WebDriver driver)
    {
        super(driver);
    }

    public void searchForPatient(String patientName)
    {
        type(searchTextBox, patientName, "patient search text box");
    }

    public boolean hasSearchResult()
    {
        return isDisplayed(searchResult, "patient search result");
    }

    public void openSearchResult()
    {
        click(searchResult, "patient search result");
    }

    public boolean hasEmptyResultMessage()
    {
        return isDisplayed(emptyresultmsg, "empty patient search message");
    }
}
