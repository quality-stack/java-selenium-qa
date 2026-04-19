package com.rideroundtrip.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rideroundtrip.features.PatientNeedsFeature;
import com.rideroundtrip.features.SelectPatientsFeature;
import com.rideroundtrip.features.SetupTripFeature;
import com.rideroundtrip.generic.AuthenticatedTest;
import com.rideroundtrip.generic.TestDataFactory;
import com.rideroundtrip.generic.ValidationOutcome;

public class BookTripTest extends AuthenticatedTest
{
    @DataProvider(name = "tripData")
    public Object[][] tripData()
    {
        return TestDataFactory.tripBookingData();
    }

    @Test(dataProvider = "tripData")
    public void bookTrip(String patientName, String tripType, String repeats, String transportType, String payerType)
    {
        requireConfig("trip.patient.name");
        loginWithConfiguredUser();

        SelectPatientsFeature patientFeature = new SelectPatientsFeature(driver);
        SetupTripFeature setupTripFeature = new SetupTripFeature(driver);
        PatientNeedsFeature patientNeedsFeature = new PatientNeedsFeature(driver);

        patientFeature.search(patientName);
        patientFeature.verifySearch(ValidationOutcome.VALID);
        setupTripFeature.selectTripType(tripType);
        setupTripFeature.selectRepeats(repeats);
        patientNeedsFeature.selectVehicleType(transportType);
    }
}

