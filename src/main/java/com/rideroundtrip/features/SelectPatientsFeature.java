package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.generic.ValidationOutcome;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;

public class SelectPatientsFeature
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectPatientsFeature.class);

    private final WebDriver driver;
    private final ScheduledTripsPage scheduledTripsPage;
    private final SelectPatientPage selectPatientPage;

    public SelectPatientsFeature(WebDriver driver)
    {
        this.driver = driver;
        this.scheduledTripsPage = new ScheduledTripsPage(driver);
        this.selectPatientPage = new SelectPatientPage(driver);
    }

    public void search(String patientName)
    {
        LOGGER.info("Searching for patient");
        scheduledTripsPage.openPatientsTab();
        selectPatientPage.searchForPatient(patientName);
    }

    public void verifySearch(ValidationOutcome outcome)
    {
        if (ValidationOutcome.VALID == outcome) {
            Assert.assertTrue(selectPatientPage.hasSearchResult(), "Expected a patient search result");
            selectPatientPage.openSearchResult();
            LOGGER.info("Patient found; current URL {}", driver.getCurrentUrl());
            Reporter.log("Patinet found: " + driver.getCurrentUrl(), true);
            return;
        }

        boolean noSearchResults = !selectPatientPage.hasSearchResult() || selectPatientPage.hasEmptyResultMessage();
        Assert.assertTrue(noSearchResults, "Expected patient search to return no results");
        LOGGER.info("Patient not found");
        Reporter.log("Patient not found", true);
    }
}
