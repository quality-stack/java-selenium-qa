package com.rideroundtrip.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class baseLibrary
{
	public static WebDriver driver;
	
	@BeforeMethod
	@Parameters({"browsername","type","url"})
	public void preconditon(String browsername, String type, String url)
	{
		driver = browserFactory.OpenBrowser(browsername, type, url);
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@AfterMethod
	public void postcondition()
	{
		driver.close();
		if(driver!=null)
		{
			driver.quit();
		}
		Reporter.log("Browser Closed",true);
	}

}
