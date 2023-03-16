package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.PatientNeedsPage;
import com.rideroundtrip.pageobjects.ReviewPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;
import com.rideroundtrip.pageobjects.SetupTripPage;
import com.rideroundtrip.pageobjects.TripPaymentPage;

public class SetupTripFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage sctp;
	SelectPatientPage spp;
	SetupTripPage stp;
	PatientNeedsPage pnp;
	TripPaymentPage tpp;
	ReviewPage rp;
	
	public SetupTripFeature (WebDriver driver)
	{
		this.driver = driver;
		spp = new SelectPatientPage(driver) ;
		stp = new SetupTripPage(driver);
	}
	
	public void selectTripType(String tripType)
	{
		if(tripType == "One-Way")
		{
			stp.getOnewaybtn().click();
		}
		
		else if (tripType == "Round-Trip")
		{
			stp.getRoundtripbtn().click();
		}
		else {
			Reporter.log("Invalid Trip Type",true);
		}
	}
	
	public void selectRepeats(String n)
	{
		if(Integer.parseInt(n)>0)
		{
			stp.getRepeatcheckbox().click();
		}
	}
	
	public void selectPickuploc()
	{
		stp.getPickupbtn().click();
		if(stp.getNoresidencemsg().isDisplayed())
		{
			Reporter.log("No pickup locations are available in Residence Tab");			
		}
		else 
		{
			stp.getselectlocation().click();
			Reporter.log("Pickup location selected");
		}
	}
	
	public void selectDroploc()
	{
		stp.getDropbtn().click();
		stp.getFacilitytab().click();
		if(stp.getNoresidencemsg().isDisplayed())
		{
			Reporter.log("No drop locations are available in Facility Tab");			
		}
		else 
		{
			stp.getselectlocation().click();
			Reporter.log("Drop location selected");
			
		}
	}
	
	
}
