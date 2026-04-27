package com.rideroundtrip.generic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Builds the attachment list for a completed test run and delegates delivery to the email service.
 */
public class BuildReportMailer
{
    /** Emits report packaging progress for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildReportMailer.class);

    /**
     * Sends the post-build email when SMTP has been enabled and a test summary exists.
     */
    public static void main(String[] args)
    {
        FrameworkConfig config = FrameworkConfig.getInstance();
        if (!config.getBoolean("smtp.enabled", false)) {
            LOGGER.info("Skipping report mailer because SMTP notifications are disabled");
            return;
        }

        File summaryFile = new File("./target/email-summary.properties");
        if (!summaryFile.exists()) {
            LOGGER.warn("Skipping report mailer because the execution summary file is missing");
            return;
        }

        BuildExecutionSummary summary = readSummary(summaryFile);
        LOGGER.info("Preparing report attachments for suite '{}' test '{}'", summary.getSuiteName(), summary.getTestName());
        List<File> attachments = new ArrayList<File>();

        File testNgReport = new File("./test-output/emailable-report.html");
        if (testNgReport.exists()) {
            attachments.add(testNgReport);
            LOGGER.debug("Including TestNG emailable report");
        }

        File allureReportDirectory = new File(config.resolve("allure.report.directory", "", "./target/allure-report"));
        File zippedAllureReport = zipIfPresent(allureReportDirectory, "allure-report.zip");
        if (zippedAllureReport != null) {
            attachments.add(zippedAllureReport);
            LOGGER.debug("Including generated Allure report archive");
        }

        if (config.getBoolean("smtp.attach.raw.allure.results", false)) {
            File allureResultsDirectory = new File(config.resolve("allure.results.directory", "", "./target/allure-results"));
            File zippedAllureResults = zipIfPresent(allureResultsDirectory, "allure-results.zip");
            if (zippedAllureResults != null) {
                attachments.add(zippedAllureResults);
                LOGGER.debug("Including raw Allure results archive");
            }
        }

        LOGGER.info("Collected {} report attachments for email delivery", Integer.valueOf(attachments.size()));
        new EmailService().sendSummary(summary, attachments);
    }

    /**
     * Reconstructs the build summary from the properties file written by the listener.
     */
    static BuildExecutionSummary readSummary(File summaryFile)
    {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(summaryFile.toPath())) {
            properties.load(inputStream);
        } catch (IOException exception) {
            LOGGER.error("Unable to read build execution summary", exception);
            throw new IllegalStateException("Unable to read build execution summary", exception);
        }

        return new BuildExecutionSummary(
                properties.getProperty("suite.name", ""),
                properties.getProperty("test.name", ""),
                Long.parseLong(properties.getProperty("start.time", "0")),
                Long.parseLong(properties.getProperty("end.time", "0")),
                Integer.parseInt(properties.getProperty("total.count", "0")),
                Integer.parseInt(properties.getProperty("passed.count", "0")),
                Integer.parseInt(properties.getProperty("failed.count", "0")),
                Integer.parseInt(properties.getProperty("skipped.count", "0")));
    }

    /**
     * Creates a zip archive for the provided report directory when it exists.
     */
    private static File zipIfPresent(File source, String fileName)
    {
        if (source == null || !source.exists()) {
            LOGGER.debug("Skipping zip creation because source is unavailable");
            return null;
        }

        File zipFile = new File("./target/" + fileName);
        try (OutputStream outputStream = Files.newOutputStream(
                zipFile.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
             ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zip(source, source.getName(), zipOutputStream);
            LOGGER.info("Created archive {}", zipFile.getName());
            return zipFile;
        } catch (IOException exception) {
            LOGGER.error("Unable to create archive for {}", source.getAbsolutePath(), exception);
            throw new IllegalStateException("Unable to create archive for " + source.getAbsolutePath(), exception);
        }
    }

    /**
     * Recursively writes a file or directory into the supplied zip output stream.
     */
    private static void zip(File source, String entryName, ZipOutputStream zipOutputStream) throws IOException
    {
        if (source.isDirectory()) {
            File[] children = source.listFiles();
            if (children == null || children.length == 0) {
                zipOutputStream.putNextEntry(new ZipEntry(entryName + "/"));
                zipOutputStream.closeEntry();
                return;
            }

            for (File child : children) {
                zip(child, entryName + "/" + child.getName(), zipOutputStream);
            }
            return;
        }

        zipOutputStream.putNextEntry(new ZipEntry(entryName));
        Files.copy(source.toPath(), zipOutputStream);
        zipOutputStream.closeEntry();
    }
}

