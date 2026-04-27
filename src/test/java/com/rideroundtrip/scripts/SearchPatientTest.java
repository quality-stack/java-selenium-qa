package com.rideroundtrip.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rideroundtrip.features.SelectPatientsFeature;
import com.rideroundtrip.generic.AuthenticatedTest;
import com.rideroundtrip.generic.TestDataFactory;
import com.rideroundtrip.generic.ValidationOutcome;

/**
 * Verifies patient search results after authenticating with the configured user.
 */
public class SearchPatientTest extends AuthenticatedTest
{
    /**
     * Supplies the patient-search test inputs from the shared test-data factory.
     */
    @DataProvider(name = "patientData")
    public Object[][] patientData()
    {
        return TestDataFactory.patientSearchData();
    }

    /**
     * Searches for a patient and validates whether a result should or should not appear.
     */
    @Test(dataProvider = "patientData")
    public void searchPatientScenarios(String patientName, ValidationOutcome outcome)
    {
        if (ValidationOutcome.VALID == outcome) {
            requireConfig("patients.valid.name");
        } else {
            requireConfig("patients.invalid.name");
        }
        loginWithConfiguredUser();

        SelectPatientsFeature patientFeature = new SelectPatientsFeature(driver);
        patientFeature.search(patientName);
        patientFeature.verifySearch(outcome);
    }
}
