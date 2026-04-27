package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.PatientNeedsPage;
import com.rideroundtrip.pageobjects.ReviewPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;
import com.rideroundtrip.pageobjects.SetupTripPage;
import com.rideroundtrip.pageobjects.TripPaymentPage;

/**
 * Populates the patient-needs step based on the selected transportation type.
 */
public class PatientNeedsFeature 
{
    /** Emits patient-needs flow progress for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientNeedsFeature.class);
    /** Driver shared by the patient-needs flow. */
    WebDriver driver;
    LoginPage loginpage;
    ScheduledTripsPage sctp;
    SelectPatientPage spp;
    SetupTripPage stp;
    PatientNeedsPage pnp;
    TripPaymentPage tpp;
    ReviewPage rp;
    
    /**
     * Creates the feature layer for patient-needs interactions.
     */
    public PatientNeedsFeature (WebDriver driver)
    {
        this.driver = driver;
        stp = new SetupTripPage(driver) ;
        pnp = new PatientNeedsPage(driver);
        tpp = new TripPaymentPage(driver);
    }
    
    /**
     * Selects the requested vehicle type and fills the matching patient-needs controls.
     */
    public void selectVehicleType(String vehicleType)
    {
        LOGGER.info("Selecting vehicle type for patient-needs step");
        Select tripreason = new Select(pnp.getdrptripreason());
        if("Lyft".equalsIgnoreCase(vehicleType)) 
        {
            pnp.getAmbulatory_vehicle().click();
            pnp.getlyft_ride().click();
            pnp.getridetype_lyft().click();
            tripreason.selectByIndex(2);
            pnp.getadditional_information().sendKeys("This ride is being booked by Test Automation");
            LOGGER.debug("Configured ambulatory Lyft ride options");
//			pnp.get
        }
        
        if("Medical Sedan".equalsIgnoreCase(vehicleType))
        {
            LOGGER.debug("Medical Sedan branch reached but not yet implemented");
        }
        
        if("Wheelchair".equalsIgnoreCase(vehicleType))
        {
            LOGGER.debug("Wheelchair branch reached but not yet implemented");
        }
        
        if("Stretcher".equalsIgnoreCase(vehicleType))
        {
            LOGGER.debug("Stretcher branch reached but not yet implemented");
        }
        
    }
}
