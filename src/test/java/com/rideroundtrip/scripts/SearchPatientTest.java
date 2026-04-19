package com.rideroundtrip.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rideroundtrip.features.SelectPatientsFeature;
import com.rideroundtrip.generic.AuthenticatedTest;
import com.rideroundtrip.generic.TestDataFactory;

public class SearchPatientTest extends AuthenticatedTest
{
  @DataProvider(name = "patientData")
  public Object[][] patientData()
  {
      return TestDataFactory.patientSearchData();
  }

  @Test(dataProvider = "patientData")
  public void searchPatientScenarios(String patientName, Integer validationType)
  {
      if (validationType.intValue() == 1) {
          requireConfig("patients.valid.name");
      } else {
          requireConfig("patients.invalid.name");
      }
      loginWithConfiguredUser();

      SelectPatientsFeature patientFeature = new SelectPatientsFeature(driver);
      patientFeature.search(patientName);
      patientFeature.verifysearch(validationType.intValue());
  }
}

