package com.rideroundtrip.generic;

public final class TestDataFactory
{
    private static final FrameworkConfig CONFIG = FrameworkConfig.getInstance();

    private TestDataFactory()
    {
    }

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
