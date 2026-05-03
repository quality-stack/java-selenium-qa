package com.rideroundtrip.generic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.qameta.allure.Allure;

/**
 * TestNG listener that tracks execution counts, captures screenshots, and writes suite summaries.
 */
public class testngListner implements ITestListener
{
    /** Emits suite and screenshot events for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(testngListner.class);
    /** Shared counters used to build the suite execution summary. */
    public static int totalTestExecuted, successcount, failcount, skipcount=0;

    /**
     * Records the start of a test method.
     */
    @Override
    public void onTestStart(ITestResult result)
    {
        totalTestExecuted++;
        LOGGER.info("Test started: {}", result.getName());
        Reporter.log(result.getName()+"script execution starts at "+ new Date(),true);
    }

    /**
     * Records a successful test result.
     */
    @Override
    public void onTestSuccess(ITestResult result)
    {
        successcount++;
        LOGGER.info("Test passed: {}", result.getName());
        Reporter.log(result.getName()+" script is passed",true);
    }

    /**
     * Records a failed test result and attaches a screenshot when one can be captured.
     */
    @Override
    public void onTestFailure(ITestResult result)
    {
        failcount++;
        LOGGER.error("Test failed: {}", result.getName(), result.getThrowable());
        Reporter.log(result.getName()+" script is failed",true);

        if (!(baseLibrary.getDriver() instanceof TakesScreenshot)) {
            LOGGER.debug("Skipping screenshot capture because the active driver does not support screenshots");
            return;
        }
        TakesScreenshot ts = (TakesScreenshot) baseLibrary.getDriver();
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File screenshotDirectory = new File("./screenshots");
        if (!screenshotDirectory.exists()) {
            screenshotDirectory.mkdirs();
        }
        File destFile = new File(screenshotDirectory, result.getName()+".png");
        try
        {
            Files.copy(srcFile.toPath(),destFile.toPath());
            try (InputStream screenshotStream = Files.newInputStream(destFile.toPath()))
            {
                Allure.addAttachment(result.getName() + " screenshot", "image/png", screenshotStream, ".png");
            }
            LOGGER.info("Stored failure screenshot at {}", destFile.getAbsolutePath());
        }
        catch (IOException e)
        {
            LOGGER.error("Unable to capture screenshot for {}", result.getName(), e);
        }
        Reporter.log("Screenshot is captured",true);
    }

    /**
     * Records a skipped test result.
     */
    @Override
    public void onTestSkipped(ITestResult result)
    {
        skipcount++;
        LOGGER.info("Test skipped: {}", result.getName());
        Reporter.log(result.getName()+" script is skipped",true);
    }

    /**
     * Required TestNG hook for partial-success scenarios.
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Resets counters at the beginning of a suite.
     */
    @Override
    public void onStart(ITestContext context)
    {
        totalTestExecuted = 0;
        successcount = 0;
        failcount = 0;
        skipcount = 0;
        LOGGER.info("Suite started: {}", context.getSuite().getName());
        Reporter.log("Suite Execution Started at "+new Date(),true);
    }

    /**
     * Logs suite totals and persists a summary file for downstream reporting.
     */
    @Override
    public void onFinish(ITestContext context)
    {
        LOGGER.info(
                "Suite finished: {} passed={} failed={} skipped={}",
                context.getSuite().getName(),
                Integer.valueOf(successcount),
                Integer.valueOf(failcount),
                Integer.valueOf(skipcount));
        Reporter.log("Suite Execution ended at "+new Date(),true);
        Reporter.log("Total scripts passed "+successcount,true);
        Reporter.log("Total scripts failed "+failcount,true);
        Reporter.log("Total scripts skipped "+skipcount,true);
        writeExecutionSummary(context);
    }

    /**
     * Persists the suite counters to disk so later build steps can read them safely.
     */
    private void writeExecutionSummary(ITestContext context)
    {
        File targetDirectory = new File("./target");
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        Properties summary = new Properties();
        summary.setProperty("suite.name", context.getSuite().getName());
        summary.setProperty("test.name", context.getName());
        summary.setProperty("start.time", Long.toString(context.getStartDate().getTime()));
        summary.setProperty("end.time", Long.toString(context.getEndDate().getTime()));
        summary.setProperty("total.count", Integer.toString(totalTestExecuted));
        summary.setProperty("passed.count", Integer.toString(successcount));
        summary.setProperty("failed.count", Integer.toString(failcount));
        summary.setProperty("skipped.count", Integer.toString(skipcount));

        File summaryFile = new File(targetDirectory, "email-summary.properties");
        try (java.io.OutputStream outputStream = Files.newOutputStream(summaryFile.toPath()))
        {
            summary.store(outputStream, "Execution summary");
            LOGGER.debug("Wrote execution summary to {}", summaryFile.getAbsolutePath());
        }
        catch (IOException exception)
        {
            LOGGER.error("Unable to write execution summary", exception);
            throw new IllegalStateException("Unable to write execution summary", exception);
        }
    }
}
