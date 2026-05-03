package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.PatientNeedsPage;
import com.rideroundtrip.pageobjects.ReviewPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;
import com.rideroundtrip.pageobjects.SetupTripPage;
import com.rideroundtrip.pageobjects.TripPaymentPage;

/**
 * Handles trip-type and location-selection actions during trip setup.
 */
public class SetupTripFeature
{
    /** Emits trip-setup flow progress for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SetupTripFeature.class);
    /** Driver shared by the trip-setup flow. */
    WebDriver driver;
    LoginPage loginpage;
    ScheduledTripsPage sctp;
    SelectPatientPage spp;
    SetupTripPage stp;
    PatientNeedsPage pnp;
    TripPaymentPage tpp;
    ReviewPage rp;

    /**
     * Creates the feature layer for trip-setup interactions.
     */
    public SetupTripFeature (WebDriver driver)
    {
        this.driver = driver;
        spp = new SelectPatientPage(driver) ;
        stp = new SetupTripPage(driver);
    }

    /**
     * Selects either a one-way or round-trip journey type.
     */
    public void selectTripType(String tripType)
    {
        LOGGER.info("Selecting trip type");
        if("One-Way".equalsIgnoreCase(tripType) || "OneWay".equalsIgnoreCase(tripType))
        {
            stp.getOnewaybtn().click();
            LOGGER.debug("Selected one-way trip");
        }

        else if ("Round-Trip".equalsIgnoreCase(tripType) || "RoundTrip".equalsIgnoreCase(tripType))
        {
            stp.getRoundtripbtn().click();
            LOGGER.debug("Selected round-trip");
        }
        else {
            LOGGER.warn("Received unsupported trip type");
            Reporter.log("Invalid Trip Type",true);
        }
    }

    /**
     * Enables repeat scheduling when the supplied repeat count is greater than zero.
     */
    public void selectRepeats(String n)
    {
        LOGGER.info("Evaluating repeat-trip configuration");
        if(Integer.parseInt(n)>0)
        {
            stp.getRepeatcheckbox().click();
            LOGGER.debug("Enabled repeat trip option");
        }
    }

    /**
     * Chooses the first available pickup location when one is present.
     */
    public void selectPickuploc()
    {
        LOGGER.info("Selecting pickup location");
        stp.getPickupbtn().click();
        if(stp.getNoresidencemsg().isDisplayed())
        {
            LOGGER.info("No pickup locations available in residence tab");
            Reporter.log("No pickup locations are available in Residence Tab");
        }
        else
        {
            stp.getselectlocation().click();
            LOGGER.debug("Pickup location selected");
            Reporter.log("Pickup location selected");
        }
    }

    /**
     * Chooses the first available drop-off location when one is present.
     */
    public void selectDroploc()
    {
        LOGGER.info("Selecting drop location");
        stp.getDropbtn().click();
        stp.getFacilitytab().click();
        if(stp.getNoresidencemsg().isDisplayed())
        {
            LOGGER.info("No drop locations available in facility tab");
            Reporter.log("No drop locations are available in Facility Tab");
        }
        else
        {
            stp.getselectlocation().click();
            LOGGER.debug("Drop location selected");
            Reporter.log("Drop location selected");

        }
    }


}
