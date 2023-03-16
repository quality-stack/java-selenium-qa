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

public class TripPaymentFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage sctp;
	SelectPatientPage spp;
	SetupTripPage stp;
	PatientNeedsPage pnp;
	TripPaymentPage tpp;
	ReviewPage rp;
	
	public TripPaymentFeature (WebDriver driver)
	{
		this.driver = driver;
		pnp = new PatientNeedsPage(driver) ;
		tpp = new TripPaymentPage(driver);
		rp = new ReviewPage(driver);
	}
	
	void selectPayerType(String payerType)
	{
		Select payerdropdown = new Select(tpp.getPayerType());
		Select facilityprogram = new Select(tpp.getFacilityProgram());
		if(payerType == "Facility")
		{
			payerdropdown.selectByIndex(1);
			facilityprogram.selectByIndex(1);
		}
		
		else if(payerType == "Medicaid")
		{
			payerdropdown.selectByVisibleText("Medicaid");
			facilityprogram.selectByIndex(1);
			tpp.getPlanName().sendKeys("Test Plan");
			tpp.getMedicaidID().sendKeys("1");
			tpp.getZipcode().sendKeys("76500");
		}
		
		else if(payerType == "Patient")
		{
			payerdropdown.selectByVisibleText("Patient");
			facilityprogram.selectByIndex(1);
			tpp.getPatientEmail().sendKeys("patient@rideroundtrip.com");
			tpp.getCardName().sendKeys("Test Patient");
			tpp.getCardNumber().sendKeys("4242424242424242");
			tpp.getCardExpiry().sendKeys("0122");
			tpp.getCardCVC().sendKeys("123");
		}
		
		else if(payerType == "Private Insurance")
		{
			payerdropdown.selectByVisibleText("Private Insurance");
			facilityprogram.selectByIndex(1);
			tpp.getCarrier().sendKeys("Test Carrier");
			tpp.getPlanType().sendKeys("Test Plan");
			tpp.getInsuranceID().sendKeys("1");
		}
		
		tpp.getContinuebtn().click();
	}
	
	public void verifyPayerType()
	{
		
	}
}
