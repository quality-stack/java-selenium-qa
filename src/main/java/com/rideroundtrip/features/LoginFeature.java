package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.pageobjects.LoginPage;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;;

public class LoginFeature 
{
	WebDriver driver;
	LoginPage loginpage;
	ScheduledTripsPage sp;
	
	public LoginFeature(WebDriver driver) 
	{
		this.driver= driver;
		loginpage = new LoginPage(driver);
		sp = new ScheduledTripsPage(driver);
	}

	public void login(String username, String password) 
	{
		loginpage.getEmailTextBox().sendKeys(username);
		loginpage.getNextbtn().click();
		loginpage.getPwdTextBox().sendKeys(password);
		loginpage.getSigninbtn().click();
	}
	
	public void verifylogin(int tc)
	{
		if(tc==1)
		{
			String exptitle = "Scheduled Trips | RoundTrip";
			String acttitle = driver.getTitle();
			Assert.assertEquals(exptitle, acttitle);
			Reporter.log("valid login verified",true);
		}
		if (tc==2) 
		{
			Assert.assertTrue(loginpage.getInvalidmsg().isDisplayed());
			Reporter.log("invalid login verified",true);
		}
		
	}
}
