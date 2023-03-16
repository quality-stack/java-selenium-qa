package com.rideroundtrip.generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;

public class testngListner implements ITestListener
{
	public static int totalTestExecuted, successcount, failcount, skipcount=0;
	public void onTestStart(ITestResult result) 
	{
		totalTestExecuted++;
		Reporter.log(result.getName()+"script execution starts at "+ new Date(),true);	
	}

	public void onTestSuccess(ITestResult result) 
	{
		successcount++;
		Reporter.log(result.getName()+" script is passed",true);	
	}

	public void onTestFailure(ITestResult result) 
	{
		failcount++;
		Reporter.log(result.getName()+" script is failed",true);
		
		TakesScreenshot ts = (TakesScreenshot) baseLibrary.driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots"+result.getName()+".png");
		try 
		{
			Files.copy(srcFile,destFile);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Reporter.log("Screenshot is captured",true);
	}

	public void onTestSkipped(ITestResult result) 
	{
		skipcount++;
		Reporter.log(result.getName()+" script is skipped",true);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) 
	{
		Reporter.log("Suite Execution Started at "+new Date(),true);
	}

	public void onFinish(ITestContext context)
	{
		Reporter.log("Suite Execution ended at "+new Date(),true);
		Reporter.log("Total scripts passed "+successcount,true);
		Reporter.log("Total scripts failed "+failcount,true);
		Reporter.log("Total scripts skipped "+skipcount,true);	
	}
}
