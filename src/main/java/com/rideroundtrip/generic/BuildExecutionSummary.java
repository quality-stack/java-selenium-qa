package com.rideroundtrip.generic;

/**
 * Immutable summary of one test execution used for email and post-build reporting.
 */
public class BuildExecutionSummary
{
    /** Name of the executed suite. */
    private final String suiteName;
    /** Name of the executed TestNG test block. */
    private final String testName;
    /** Suite start timestamp in epoch milliseconds. */
    private final long startTime;
    /** Suite end timestamp in epoch milliseconds. */
    private final long endTime;
    /** Total executed test count. */
    private final int totalCount;
    /** Number of tests that passed. */
    private final int passedCount;
    /** Number of tests that failed. */
    private final int failedCount;
    /** Number of tests that were skipped. */
    private final int skippedCount;

    /**
     * Creates a summary object from the collected suite counters.
     */
    public BuildExecutionSummary(
            String suiteName,
            String testName,
            long startTime,
            long endTime,
            int totalCount,
            int passedCount,
            int failedCount,
            int skippedCount)
    {
        this.suiteName = suiteName;
        this.testName = testName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCount = totalCount;
        this.passedCount = passedCount;
        this.failedCount = failedCount;
        this.skippedCount = skippedCount;
    }

    /**
     * Returns the executed suite name.
     */
    public String getSuiteName()
    {
        return suiteName;
    }

    /**
     * Returns the executed TestNG test block name.
     */
    public String getTestName()
    {
        return testName;
    }

    /**
     * Returns the suite start time in epoch milliseconds.
     */
    public long getStartTime()
    {
        return startTime;
    }

    /**
     * Returns the suite end time in epoch milliseconds.
     */
    public long getEndTime()
    {
        return endTime;
    }

    /**
     * Returns the total number of executed tests.
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * Returns the number of passed tests.
     */
    public int getPassedCount()
    {
        return passedCount;
    }

    /**
     * Returns the number of failed tests.
     */
    public int getFailedCount()
    {
        return failedCount;
    }

    /**
     * Returns the number of skipped tests.
     */
    public int getSkippedCount()
    {
        return skippedCount;
    }
}

