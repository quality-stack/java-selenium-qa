package com.rideroundtrip.generic;

import java.io.File;

public class BuildFailureGuard
{
    public static void main(String[] args)
    {
        File summaryFile = new File("./target/email-summary.properties");
        if (!summaryFile.exists()) {
            throw new IllegalStateException("Build summary file was not generated, so the build result cannot be validated.");
        }

        BuildExecutionSummary summary = BuildReportMailer.readSummary(summaryFile);
        if (summary.getFailedCount() > 0) {
            throw new IllegalStateException(
                    "Test execution completed with failures. Failed tests: " + summary.getFailedCount());
        }
    }
}

