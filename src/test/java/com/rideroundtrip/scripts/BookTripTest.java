package com.rideroundtrip.scripts;

import org.testng.annotations.Test;

import com.rideroundtrip.features.LoginFeature;
import com.rideroundtrip.features.PatientNeedsFeature;
import com.rideroundtrip.features.SelectPatientsFeature;
import com.rideroundtrip.generic.baseLibrary;
import com.rideroundtrip.generic.excelUtility;

public class BookTripTest extends baseLibrary
{	
	@Test (priority=1)
	public void bookLyft(String patinetName, String tripType, String repeats, String payerType)
	{
		LoginFeature lf = new LoginFeature(driver);
		SelectPatientsFeature spf = new SelectPatientsFeature(driver);
		PatientNeedsFeature pnf = new PatientNeedsFeature(driver);
		
		excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
		String username = eu.readData("Login", 1, 1);
		String password = eu.readData("Login", 1, 2);
		lf.login(username, password);
		lf.verifylogin(1);
		
		spf.search(patinetName);
		spf.verifysearch(1);
		
	}
	
	@Test (priority=2)
	public void bookMedicalSedan(String patinetName, String tripType, String repeats, String transportType, String payerType)
	{
		LoginFeature lf = new LoginFeature(driver);
		SelectPatientsFeature spf = new SelectPatientsFeature(driver);
		
		excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
		String username = eu.readData("Login", 1, 1);
		String password = eu.readData("Login", 1, 2);
		lf.login(username, password);
		lf.verifylogin(1);
		
		spf.search(patinetName);
		spf.verifysearch(1);
	}
	
	@Test (priority=3)
	public void bookAmbulatory(String patinetName, String tripType, String repeats, String transportType, String payerType)
	{
		LoginFeature lf = new LoginFeature(driver);
		SelectPatientsFeature spf = new SelectPatientsFeature(driver);
		
		excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
		String username = eu.readData("Login", 1, 1);
		String password = eu.readData("Login", 1, 2);
		lf.login(username, password);
		lf.verifylogin(1);
		
		spf.search(patinetName);
		spf.verifysearch(1);
	}
	
	@Test (priority=4)
	public void bookWheelchair(String patinetName, String tripType, String repeats, String transportType, String payerType)
	{
		LoginFeature lf = new LoginFeature(driver);
		SelectPatientsFeature spf = new SelectPatientsFeature(driver);
		
		excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
		String username = eu.readData("Login", 1, 1);
		String password = eu.readData("Login", 1, 2);
		lf.login(username, password);
		lf.verifylogin(1);
		
		spf.search(patinetName);
		spf.verifysearch(1);
	}
	
	@Test (priority=5)
	public void bookStretcher(String patinetName, String tripType, String repeats, String transportType, String payerType)
	{
		LoginFeature lf = new LoginFeature(driver);
		SelectPatientsFeature spf = new SelectPatientsFeature(driver);
		
		excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
		String username = eu.readData("Login", 1, 1);
		String password = eu.readData("Login", 1, 2);
		lf.login(username, password);
		lf.verifylogin(1);
		
		spf.search(patinetName);
		spf.verifysearch(1);
	}
}
