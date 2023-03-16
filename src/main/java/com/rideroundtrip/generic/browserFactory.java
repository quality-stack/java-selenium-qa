package com.rideroundtrip.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class browserFactory
{
	static WebDriver driver;
	
	public static WebDriver openBrowser(String browser,String session,String URL)
	{
		if (session.equalsIgnoreCase("normal"))
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				driver = new ChromeDriver(options);
				Reporter.log("Chrome Launched...!",true);
			}
			else if(browser.equalsIgnoreCase("edge")) 
			{
				System.setProperty("webdriver.edge.driver", "./exefiles/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				Reporter.log("Edge Launched...!",true);
			}
			else if(browser.equalsIgnoreCase("firefox")) 
			{
				System.setProperty("webdriver.gecko.driver", "./exefiles/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				Reporter.log("Firefox Launched...!",true);
			}
			else if(browser.equalsIgnoreCase("ie")) 
			{
				System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				Reporter.log("IE Launched...!",true);
				driver.manage().window().maximize();
			}
		}
		
		else if (session.equalsIgnoreCase("incognito") && (browser.equalsIgnoreCase("chrome")))
			{
				System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("start-maximized");
				//options.addArguments("--window-size=375,812");
				driver = new ChromeDriver(options);
				Reporter.log("Chrome Incognito Launched...!",true);
		}
		
		driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		return driver;	
	}
}
