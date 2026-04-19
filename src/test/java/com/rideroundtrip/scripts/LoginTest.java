package com.rideroundtrip.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rideroundtrip.features.LoginFeature;
import com.rideroundtrip.generic.TestDataFactory;
import com.rideroundtrip.generic.baseLibrary;

public class LoginTest extends baseLibrary
{
  @DataProvider(name = "loginData")
  public Object[][] loginData()
  {
      return TestDataFactory.loginData();
  }

  @Test(dataProvider = "loginData")
  public void loginScenarios(String username, String password, Integer validationType)
  {
      if (validationType.intValue() == 1) {
          requireConfig("app.url", "app.username", "app.password", "app.expectedTitle");
      } else {
          requireConfig("app.url", "invalid.username", "invalid.password");
      }

      LoginFeature loginFeature = new LoginFeature(driver);
      loginFeature.login(username, password);
      loginFeature.verifylogin(validationType.intValue());
  }
}

