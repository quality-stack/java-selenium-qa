package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;

public class ScheduledTripFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage stp;
	SelectPatientPage spp;
	
	public ScheduledTripFeature(WebDriver driver) 
	{
		this.driver= driver;
		loginpage = new LoginPage(driver);
		stp = new ScheduledTripsPage(driver);
		spp = new SelectPatientPage(driver);
		
	}
	
	public void BookTrip()
	{
		stp.getBooktripbtn().click();
		String exptitle = "Select Patient | RoundTrip";
		String acttitle = driver.getTitle();
		Assert.assertEquals(exptitle, acttitle);
		Reporter.log("Entered Book Trip",true);
	}
}
