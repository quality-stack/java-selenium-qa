# java-selenium-qa

Reusable Java Selenium/TestNG framework with central configuration, data-driven tests, and browser setup that is no longer tied to one machine.

## What changed

- Removed the hard-coded local JDK path from `pom.xml`.
- Centralized framework settings in `src/test/resources/framework.properties`.
- Made browser setup configurable through TestNG parameters, Java system properties, environment variables, or the properties file.
- Replaced brittle test method signatures with TestNG data providers.
- Added a reusable authenticated test base for application-specific flows.
- Fixed obvious framework bugs such as broken RedBus locators, string comparison with `==`, and unsafe screenshot handling.
- Added Allure reporting with TestNG integration and screenshot attachments on failure.

## Default execution

The default suite still runs the public RedBus search flow:

```bash
mvn test
```

Allure result files are written to `target/allure-results`.

SMTP execution email can also be triggered automatically after the Maven test phase when SMTP is enabled.

## Viewing Allure reports

Generate the HTML report:

```bash
mvn allure:report
```

Open the generated report locally:

```bash
mvn allure:serve
```

## Automatic email on each build execution

The framework can send an SMTP summary email automatically after every `mvn test` execution. The TestNG listener writes the execution summary, Maven generates the Allure report, the mailer sends the email with both TestNG and Allure artifacts attached, and then a build guard fails the Maven run if there were test failures.

Set these values in `src/test/resources/framework.properties` or pass them as system properties:

- `smtp.enabled=true`
- `smtp.host`
- `smtp.port`
- `smtp.username`
- `smtp.password`
- `smtp.from`
- `smtp.to`

Optional values:

- `smtp.cc`
- `smtp.auth`
- `smtp.starttls`
- `smtp.ssl`
- `smtp.subject.prefix`

Example:

```bash
mvn test -Dsmtp.enabled=true -Dsmtp.host=smtp.gmail.com -Dsmtp.port=587 -Dsmtp.username=your-user@gmail.com -Dsmtp.password=your-app-password -Dsmtp.from=your-user@gmail.com -Dsmtp.to=qa-team@example.com,dev-team@example.com
```

The email includes:

- suite and test names
- start and finish times
- passed, failed, skipped, and total counts
- the TestNG `emailable-report.html` as an attachment when available
- a zipped generated Allure report artifact
- a zipped Allure raw results artifact

If tests fail, the email step still runs first and the Maven build fails afterward so CI still gets the correct failed status.

## Running with custom configuration

You can override any value with Java system properties:

```bash
mvn test -Dapp.url=https://example.com -Dbrowser=firefox -Dbus.source=Pune -Dbus.destination=Mumbai
```

For app-specific flows like login, patient search, and trip booking, set the relevant values in `src/test/resources/framework.properties` or pass them with `-D...`.

## Key configuration values

- `browser`
- `session.type`
- `app.url`
- `app.username`
- `app.password`
- `app.expectedTitle`
- `invalid.username`
- `invalid.password`
- `patients.valid.name`
- `patients.invalid.name`
- `trip.patient.name`
- `trip.type`
- `trip.repeats`
- `trip.transport.type`
- `bus.source`
- `bus.destination`

## Notes

- Browser driver paths are optional and can be set with properties such as `driver.chrome.path`.
- If required app-specific values are missing, those tests are skipped instead of failing for configuration reasons.
