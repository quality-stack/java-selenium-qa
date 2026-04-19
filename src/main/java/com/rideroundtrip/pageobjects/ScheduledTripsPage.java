package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public ScheduledTripsPage(WebDriver driver)
    {
        super(driver);
    }

    public void openPatientsTab()
    {
        click(patientstab, "patients tab");
    }

    public void openBookTrip()
    {
        click(booktripbtn, "book trip button");
    }
}
