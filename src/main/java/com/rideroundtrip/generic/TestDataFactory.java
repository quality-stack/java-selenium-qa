package com.rideroundtrip.generic;

/**
 * Produces centralized data-provider payloads for the TestNG suites.
 */
public final class TestDataFactory
{
    /** Shared configuration source used to populate test inputs. */
    private static final FrameworkConfig CONFIG = FrameworkConfig.getInstance();

    /** Prevents utility-style construction. */
    private TestDataFactory()
    {
    }

    /**
     * Returns positive and negative login scenarios for the login suite.
     */
    public static Object[][] loginData()
    {
        return new Object[][] {
            {
                CONFIG.get("app.username"),
                CONFIG.get("app.password"),
                ValidationOutcome.VALID
            },
            {
                CONFIG.get("invalid.username"),
                CONFIG.get("invalid.password"),
                ValidationOutcome.INVALID
            }
        };
    }

    /**
     * Returns positive and negative patient-search scenarios for the patient suite.
     */
    public static Object[][] patientSearchData()
    {
        return new Object[][] {
            {
                CONFIG.get("patients.valid.name"),
                ValidationOutcome.VALID
            },
            {
                CONFIG.get("patients.invalid.name"),
                ValidationOutcome.INVALID
            }
        };
    }

    /**
     * Returns the configured trip-booking payload used by the booking suite.
     */
    public static Object[][] tripBookingData()
    {
        return new Object[][] {
            {
                CONFIG.get("trip.patient.name"),
                CONFIG.resolve("trip.type", "", "OneWay"),
                CONFIG.resolve("trip.repeats", "", "No"),
                CONFIG.resolve("trip.transport.type", "", "Lyft"),
                CONFIG.resolve("trip.payer.type", "", "Insurance")
            }
        };
    }
}
