package com.rideroundtrip.generic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.qameta.allure.Allure;

public class testngListner implements ITestListener
{
	public static int totalTestExecuted, successcount, failcount, skipcount=0;
	@Override
	public void onTestStart(ITestResult result) 
	{
		totalTestExecuted++;
		Reporter.log(result.getName()+"script execution starts at "+ new Date(),true);	
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		successcount++;
		Reporter.log(result.getName()+" script is passed",true);	
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		failcount++;
		Reporter.log(result.getName()+" script is failed",true);
		
		if (!(baseLibrary.getDriver() instanceof TakesScreenshot)) {
			return;
		}
		TakesScreenshot ts = (TakesScreenshot) baseLibrary.getDriver();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File screenshotDirectory = new File("./screenshots");
		if (!screenshotDirectory.exists()) {
			screenshotDirectory.mkdirs();
		}
		File destFile = new File(screenshotDirectory, result.getName()+".png");
		try 
		{
			Files.copy(srcFile.toPath(),destFile.toPath());
			try (InputStream screenshotStream = Files.newInputStream(destFile.toPath()))
			{
				Allure.addAttachment(result.getName() + " screenshot", "image/png", screenshotStream, ".png");
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Reporter.log("Screenshot is captured",true);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		skipcount++;
		Reporter.log(result.getName()+" script is skipped",true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) 
	{
		Reporter.log("Suite Execution Started at "+new Date(),true);
	}

	@Override
	public void onFinish(ITestContext context)
	{
		Reporter.log("Suite Execution ended at "+new Date(),true);
		Reporter.log("Total scripts passed "+successcount,true);
		Reporter.log("Total scripts failed "+failcount,true);
		Reporter.log("Total scripts skipped "+skipcount,true);	
	}
}
