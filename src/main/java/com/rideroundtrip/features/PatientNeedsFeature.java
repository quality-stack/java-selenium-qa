package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.PatientNeedsPage;
import com.rideroundtrip.pageobjects.ReviewPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;
import com.rideroundtrip.pageobjects.SetupTripPage;
import com.rideroundtrip.pageobjects.TripPaymentPage;

public class PatientNeedsFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage sctp;
	SelectPatientPage spp;
	SetupTripPage stp;
	PatientNeedsPage pnp;
	TripPaymentPage tpp;
	ReviewPage rp;
	
	public PatientNeedsFeature (WebDriver driver)
	{
		this.driver = driver;
		stp = new SetupTripPage(driver) ;
		pnp = new PatientNeedsPage(driver);
		tpp = new TripPaymentPage(driver);
	}
	
	public void selectVehicleType(String vehicleType)
	{
		Select tripreason = new Select(pnp.getdrptripreason());
		if(vehicleType == "Lyft") 
		{
			pnp.getAmbulatory_vehicle().click();
			pnp.getlyft_ride().click();
			pnp.getridetype_lyft().click();
			tripreason.selectByIndex(2);
			pnp.getadditional_information().sendKeys("This ride is being booked by Test Automation");
//			pnp.get
		}
		
		if(vehicleType == "Medical Sedan")
		{
			
		}
		
		if(vehicleType == "Wheelchair")
		{
			
		}
		
		if(vehicleType == "Stretcher")
		{
			
		}
		
	}
}
