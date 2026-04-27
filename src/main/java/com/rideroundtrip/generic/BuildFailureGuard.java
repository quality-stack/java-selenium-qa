package com.rideroundtrip.generic;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Re-fails the Maven build after reporting has completed when the suite recorded failed tests.
 */
public class BuildFailureGuard
{
    /** Emits non-sensitive build-guard decisions for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildFailureGuard.class);

    /**
     * Reads the execution summary from disk and throws when failures were recorded.
     */
    public static void main(String[] args)
    {
        File summaryFile = new File("./target/email-summary.properties");
        if (!summaryFile.exists()) {
            LOGGER.error("Build summary file is missing; unable to validate final build status");
            throw new IllegalStateException("Build summary file was not generated, so the build result cannot be validated.");
        }

        BuildExecutionSummary summary = BuildReportMailer.readSummary(summaryFile);
        if (summary.getFailedCount() > 0) {
            LOGGER.error("Build guard detected {} failed tests", Integer.valueOf(summary.getFailedCount()));
            throw new IllegalStateException(
                    "Test execution completed with failures. Failed tests: " + summary.getFailedCount());
        }
        LOGGER.info("Build guard verified that no tests failed");
    }
}

