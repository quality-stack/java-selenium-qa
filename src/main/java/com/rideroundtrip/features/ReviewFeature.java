package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.PatientNeedsPage;
import com.rideroundtrip.pageobjects.ReviewPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;
import com.rideroundtrip.pageobjects.SetupTripPage;
import com.rideroundtrip.pageobjects.TripPaymentPage;

/**
 * Placeholder feature layer for the final booking-review step.
 */
public class ReviewFeature 
{
    /** Driver shared by the review flow. */
    WebDriver driver;
    LoginPage loginpage;
    ScheduledTripsPage sctp;
    SelectPatientPage spp;
    SetupTripPage stp;
    PatientNeedsPage pnp;
    TripPaymentPage tpp;
    ReviewPage rp;
    
    /**
     * Creates the feature layer for review-step interactions.
     */
    public ReviewFeature (WebDriver driver)
    {
        this.driver = driver;
        
        tpp = new TripPaymentPage(driver);
        rp = new ReviewPage(driver);
    }
}
