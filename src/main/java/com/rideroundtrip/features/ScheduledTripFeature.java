package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;

/**
 * Encapsulates the initial trip-booking entry step from the scheduled-trips page.
 */
public class ScheduledTripFeature 
{
    /** Driver used to assert the current page title after navigation. */
    WebDriver driver;
    LoginPage loginpage;
    ScheduledTripsPage stp;
    SelectPatientPage spp;
    
    /**
     * Creates the feature layer for scheduled-trip interactions.
     */
    public ScheduledTripFeature(WebDriver driver) 
    {
        this.driver= driver;
        loginpage = new LoginPage(driver);
        stp = new ScheduledTripsPage(driver);
        spp = new SelectPatientPage(driver);
        
    }
    
    /**
     * Opens the Book Trip flow and verifies the patient-selection page is loaded.
     */
    public void BookTrip()
    {
        stp.openBookTrip();
        String exptitle = "Select Patient | RoundTrip";
        String acttitle = driver.getTitle();
        Assert.assertEquals(exptitle, acttitle);
        Reporter.log("Entered Book Trip",true);
    }
}
