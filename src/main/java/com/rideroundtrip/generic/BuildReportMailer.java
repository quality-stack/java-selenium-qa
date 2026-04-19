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

public class BuildReportMailer
{
    public static void main(String[] args)
    {
        FrameworkConfig config = FrameworkConfig.getInstance();
        if (!config.getBoolean("smtp.enabled", false)) {
            return;
        }

        File summaryFile = new File("./target/email-summary.properties");
        if (!summaryFile.exists()) {
            return;
        }

        BuildExecutionSummary summary = readSummary(summaryFile);
        List<File> attachments = new ArrayList<File>();

        File testNgReport = new File("./test-output/emailable-report.html");
        if (testNgReport.exists()) {
            attachments.add(testNgReport);
        }

        File allureReportDirectory = new File(config.resolve("allure.report.directory", "", "./target/allure-report"));
        File zippedAllureReport = zipIfPresent(allureReportDirectory, "allure-report.zip");
        if (zippedAllureReport != null) {
            attachments.add(zippedAllureReport);
        }

        File allureResultsDirectory = new File(config.resolve("allure.results.directory", "", "./target/allure-results"));
        File zippedAllureResults = zipIfPresent(allureResultsDirectory, "allure-results.zip");
        if (zippedAllureResults != null) {
            attachments.add(zippedAllureResults);
        }

        new EmailService().sendSummary(summary, attachments);
    }

    static BuildExecutionSummary readSummary(File summaryFile)
    {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(summaryFile.toPath())) {
            properties.load(inputStream);
        } catch (IOException exception) {
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

    private static File zipIfPresent(File source, String fileName)
    {
        if (source == null || !source.exists()) {
            return null;
        }

        File zipFile = new File("./target/" + fileName);
        try (OutputStream outputStream = Files.newOutputStream(
                zipFile.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
             ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zip(source, source.getName(), zipOutputStream);
            return zipFile;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to create archive for " + source.getAbsolutePath(), exception);
        }
    }

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
