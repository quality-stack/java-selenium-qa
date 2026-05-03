package com.rideroundtrip.generic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

/**
 * Sends execution-summary emails and attaches the generated test artifacts.
 */
public class EmailService
{
    /** Emits non-sensitive SMTP execution details for debugging. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    /** Provides SMTP and attachment configuration. */
    private final FrameworkConfig config = FrameworkConfig.getInstance();

    /**
     * Builds and sends the execution summary email for the completed run.
     */
    public void sendSummary(BuildExecutionSummary summary, List<File> attachments)
    {
        if (!config.getBoolean("smtp.enabled", false)) {
            LOGGER.info("Skipping SMTP summary because email notifications are disabled");
            Reporter.log("SMTP email notification disabled", true);
            return;
        }

        List<InternetAddress> toRecipients = parseRecipients(config.get("smtp.to"));
        if (toRecipients.isEmpty()) {
            LOGGER.warn("Skipping SMTP summary because no recipients are configured");
            Reporter.log("Skipping SMTP email because smtp.to is not configured", true);
            return;
        }

        final String username = config.get("smtp.username");
        final String password = config.get("smtp.password");
        final boolean authEnabled = config.getBoolean("smtp.auth", true);
        final String smtpHost = config.get("smtp.host");

        if (smtpHost.isEmpty()) {
            LOGGER.warn("Skipping SMTP summary because smtp.host is not configured");
            Reporter.log("Skipping SMTP email because smtp.host is not configured", true);
            return;
        }

        if (authEnabled && (username.isEmpty() || password.isEmpty())) {
            LOGGER.warn("Skipping SMTP summary because SMTP authentication is enabled but credentials are incomplete");
            Reporter.log("Skipping SMTP email because smtp.username or smtp.password is not configured", true);
            return;
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", config.resolve("smtp.port", "", "587"));
        properties.put("mail.smtp.auth", Boolean.toString(authEnabled));
        properties.put("mail.smtp.starttls.enable", Boolean.toString(config.getBoolean("smtp.starttls", true)));
        properties.put("mail.smtp.ssl.enable", Boolean.toString(config.getBoolean("smtp.ssl", false)));

        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                if (!authEnabled) {
                    return null;
                }
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            String fromAddress = config.resolve("smtp.from", "", username);
            if (fromAddress.isEmpty()) {
                LOGGER.warn("Skipping SMTP summary because no sender address is configured");
                Reporter.log("Skipping SMTP email because smtp.from or smtp.username is not configured", true);
                return;
            }

            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, toRecipients.toArray(new InternetAddress[0]));

            List<InternetAddress> ccRecipients = parseRecipients(config.get("smtp.cc"));
            if (!ccRecipients.isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, ccRecipients.toArray(new InternetAddress[0]));
            }

            String subjectPrefix = config.resolve("smtp.subject.prefix", "", "[Automation]");
            message.setSubject(subjectPrefix + " Build summary - " + summary.getTestName(), StandardCharsets.UTF_8.name());
            message.setSentDate(new Date());

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(buildHtmlSummary(summary));
            addAttachments(multipart, attachments);

            message.setContent(multipart);
            LOGGER.info(
                    "Sending SMTP summary for test '{}' to {} recipients with {} attachments",
                    summary.getTestName(),
                    Integer.valueOf(toRecipients.size()),
                    Integer.valueOf(attachments.size()));
            Transport.send(message);
            LOGGER.info("SMTP summary sent successfully");
            Reporter.log("SMTP execution summary sent to " + config.get("smtp.to"), true);
        } catch (MessagingException exception) {
            LOGGER.error("Unable to send SMTP execution summary", exception);
            throw new IllegalStateException("Unable to send SMTP execution summary", exception);
        }
    }

    /**
     * Creates the HTML body that appears in the build summary email.
     */
    private MimeBodyPart buildHtmlSummary(BuildExecutionSummary summary) throws MessagingException
    {
        MimeBodyPart bodyPart = new MimeBodyPart();
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>");
        builder.append("<h2>Automation Execution Summary</h2>");
        builder.append("<p><strong>Suite:</strong> ").append(summary.getSuiteName()).append("</p>");
        builder.append("<p><strong>Test:</strong> ").append(summary.getTestName()).append("</p>");
        builder.append("<p><strong>Started:</strong> ").append(new Date(summary.getStartTime())).append("</p>");
        builder.append("<p><strong>Finished:</strong> ").append(new Date(summary.getEndTime())).append("</p>");
        builder.append("<table border='1' cellpadding='8' cellspacing='0'>");
        builder.append("<tr><th>Total</th><th>Passed</th><th>Failed</th><th>Skipped</th></tr>");
        builder.append("<tr>")
                .append("<td>").append(summary.getTotalCount()).append("</td>")
                .append("<td>").append(summary.getPassedCount()).append("</td>")
                .append("<td>").append(summary.getFailedCount()).append("</td>")
                .append("<td>").append(summary.getSkippedCount()).append("</td>")
                .append("</tr>");
        builder.append("</table>");
        builder.append("<p><strong>Attached artifacts:</strong></p>");
        builder.append("<ul>");
        builder.append("<li>TestNG emailable report</li>");
        builder.append("<li>Generated Allure report archive</li>");
        if (config.getBoolean("smtp.attach.raw.allure.results", false)) {
            builder.append("<li>Allure raw results archive</li>");
        }
        builder.append("</ul>");
        builder.append("</body></html>");
        bodyPart.setContent(builder.toString(), "text/html; charset=UTF-8");
        return bodyPart;
    }

    /**
     * Adds all valid report files as MIME attachments.
     */
    private void addAttachments(MimeMultipart multipart, List<File> attachments) throws MessagingException
    {
        for (File attachmentFile : attachments) {
            if (attachmentFile == null || !attachmentFile.exists() || !attachmentFile.isFile()) {
                LOGGER.debug("Skipping invalid attachment entry");
                continue;
            }

            MimeBodyPart attachment = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentFile);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(attachmentFile.getName());
            multipart.addBodyPart(attachment);
            LOGGER.debug("Attached artifact {}", attachmentFile.getName());
        }
    }

    /**
     * Parses a comma-separated recipient list into validated email addresses.
     */
    private List<InternetAddress> parseRecipients(String recipients)
    {
        List<InternetAddress> addresses = new ArrayList<InternetAddress>();
        if (recipients == null || recipients.trim().isEmpty()) {
            return addresses;
        }

        String[] tokens = recipients.split(",");
        for (String token : tokens) {
            String trimmedToken = token.trim();
            if (!trimmedToken.isEmpty()) {
                try {
                    addresses.add(new InternetAddress(trimmedToken));
                } catch (MessagingException exception) {
                    throw new IllegalArgumentException("Invalid email address configured: " + trimmedToken, exception);
                }
            }
        }
        return addresses;
    }
}
