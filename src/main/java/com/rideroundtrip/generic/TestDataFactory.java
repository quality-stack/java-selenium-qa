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
                Integer.valueOf(1)
            },
            {
                CONFIG.get("invalid.username"),
                CONFIG.get("invalid.password"),
                Integer.valueOf(2)
            }
        };
    }

    public static Object[][] patientSearchData()
    {
        return new Object[][] {
            {
                CONFIG.get("patients.valid.name"),
                Integer.valueOf(1)
            },
            {
                CONFIG.get("patients.invalid.name"),
                Integer.valueOf(2)
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

