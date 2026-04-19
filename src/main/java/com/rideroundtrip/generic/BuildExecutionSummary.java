package com.rideroundtrip.generic;

public class BuildExecutionSummary
{
    private final String suiteName;
    private final String testName;
    private final long startTime;
    private final long endTime;
    private final int totalCount;
    private final int passedCount;
    private final int failedCount;
    private final int skippedCount;

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

    public String getSuiteName()
    {
        return suiteName;
    }

    public String getTestName()
    {
        return testName;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public int getPassedCount()
    {
        return passedCount;
    }

    public int getFailedCount()
    {
        return failedCount;
    }

    public int getSkippedCount()
    {
        return skippedCount;
    }
}

