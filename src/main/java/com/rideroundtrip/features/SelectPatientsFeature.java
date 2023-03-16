package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;

public class SelectPatientsFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage stp;
	SelectPatientPage spp;
	
	public SelectPatientsFeature(WebDriver driver) 
	{
		this.driver= driver;
		stp = new ScheduledTripsPage(driver);
		spp = new SelectPatientPage(driver);
	}
	
	public void search(String patientName) 
	{
		stp.getPatientstab().click();
		spp.getSearchTextBox().sendKeys(patientName);
	}
	
	public void verifysearch(int tc)
	{
		if (tc == 1)
		{
			Assert.assertTrue(spp.getSearchResult().isDisplayed());
			spp.getSearchResult().click();
			Reporter.log("Patinet found: "+driver.getCurrentUrl(),true);
		}
		
		if (tc == 2)
		{
			try 
			{
				Assert.assertFalse(spp.getSearchResult().isDisplayed());
			} 
			catch (org.openqa.selenium.NoSuchElementException e)
			{
				Reporter.log("Patient not found",true);
		    } 		
		}
	}
}
