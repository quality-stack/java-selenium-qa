package com.rideroundtrip.features;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.rideroundtrip.generic.ValidationOutcome;
import com.rideroundtrip.pageobjects.ScheduledTripsPage;
import com.rideroundtrip.pageobjects.SelectPatientPage;

/**
 * Encapsulates patient-search actions and assertions within the trip flow.
 */
public class SelectPatientsFeature
{
    /** Emits patient-search progress and assertion context. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectPatientsFeature.class);

    /** Driver used to inspect navigation state after patient selection. */
    private final WebDriver driver;
    /** Page object for the scheduled-trips landing page. */
    private final ScheduledTripsPage scheduledTripsPage;
    /** Page object for patient search and selection. */
    private final SelectPatientPage selectPatientPage;

    /**
     * Creates the feature layer for patient-search interactions.
     */
    public SelectPatientsFeature(WebDriver driver)
    {
        this.driver = driver;
        this.scheduledTripsPage = new ScheduledTripsPage(driver);
        this.selectPatientPage = new SelectPatientPage(driver);
    }

    /**
     * Opens the patient tab and enters the requested patient name into the search field.
     */
    public void search(String patientName)
    {
        LOGGER.info("Searching for patient");
        scheduledTripsPage.openPatientsTab();
        selectPatientPage.searchForPatient(patientName);
    }

    /**
     * Verifies the patient-search result against the expected scenario outcome.
     */
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
