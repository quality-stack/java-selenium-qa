package com.rideroundtrip.scripts;

import org.testng.annotations.Test;

import com.rideroundtrip.features.LoginFeature;
import com.rideroundtrip.features.SelectPatientsFeature;
import com.rideroundtrip.generic.baseLibrary;
import com.rideroundtrip.generic.excelUtility;

public class SearchPatientTest extends baseLibrary
{
  @Test (priority=1)
  public void validSearch()
  {
	  excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
	  String username = eu.readData("Login", 1, 1);
	  String password = eu.readData("Login", 1, 2);
	  LoginFeature lf = new LoginFeature(driver);
	  lf.login(username, password);
	  lf.verifylogin(1);
	  
	  String patinetName = eu.readData("Patients", 1, 1);
	  SelectPatientsFeature spf = new SelectPatientsFeature(driver);
	  spf.search(patinetName);
	  spf.verifysearch(1);
  }
  
  @Test (priority=2)
  public void invalidSearch()
  {
	  excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
	  String username = eu.readData("Login", 1, 1);
	  String password = eu.readData("Login", 1, 2);
	  LoginFeature lf = new LoginFeature(driver);
	  lf.login(username, password);
	  lf.verifylogin(1);
	  
	  String patinetName = eu.readData("Patients", 2, 1);
	  SelectPatientsFeature spf = new SelectPatientsFeature(driver);
	  spf.search(patinetName);
	  spf.verifysearch(2);
  }
}
