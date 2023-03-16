package com.rideroundtrip.scripts;

import org.testng.annotations.Test;

import com.rideroundtrip.features.LoginFeature;
import com.rideroundtrip.generic.baseLibrary;
import com.rideroundtrip.generic.excelUtility;

public class LoginTest extends baseLibrary
{
  @Test (priority=1)
  public void validLogin()  
  {
	  excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
//	  String username = "cc@rideroundtrip.com";
//	  String password = "RoundTrip1";
	  String username = eu.readData("Login", 1, 1);
	  String password = eu.readData("Login", 1, 2);
	  
	  LoginFeature lf = new LoginFeature(driver);
	  lf.login(username, password);
	  lf.verifylogin(1);
  }
  
  @Test (priority=2)
  public void invalidLogin()
  {
	  excelUtility eu = new excelUtility("./testdata/testdataRT.xlsx");
	  String username = eu.readData("Login", 2, 1);
	  String password = eu.readData("Login", 2, 2);
	  
	  LoginFeature lf = new LoginFeature(driver);
	  lf.login(username, password);
	  lf.verifylogin(2);
  }
}
