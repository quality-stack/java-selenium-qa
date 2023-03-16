package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.PatientNeedsPage;
import com.rideroundtrip.pageobjects.ReviewPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;
import com.rideroundtrip.pageobjects.SetupTripPage;
import com.rideroundtrip.pageobjects.TripPaymentPage;

public class ReviewFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage sctp;
	SelectPatientPage spp;
	SetupTripPage stp;
	PatientNeedsPage pnp;
	TripPaymentPage tpp;
	ReviewPage rp;
	
	public ReviewFeature (WebDriver driver)
	{
		this.driver = driver;
		
		tpp = new TripPaymentPage(driver);
		rp = new ReviewPage(driver);
	}
}
