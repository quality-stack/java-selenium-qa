# java-selenium-qa

Reusable Java Selenium/TestNG framework with centralized configuration, Maven-managed dependencies, and browser setup that is no longer tied to one machine.

## Current framework highlights

- Uses Maven-managed dependencies only for the active build; the legacy `jarfiles/` bundle is no longer part of the runtime classpath.
- Centralizes framework settings in `src/test/resources/framework.properties`.
- Supports configuration overrides through TestNG parameters, Java system properties, environment variables, or the properties file.
- Uses data-driven TestNG flows instead of brittle magic-number or signature-heavy test methods.
- Includes a reusable authenticated test base for app-specific flows.
- Uses explicit-wait based page interactions in the active page-object layer for more stable browser automation.
- Adds Allure reporting with TestNG integration and screenshot attachments on failure.
- Adds automatic browser-driver management, so local ChromeDriver binaries are not required by default.
- Routes framework logging through SLF4J and Log4j2 for safer and more consistent diagnostics.

## Default execution

The default suite still runs the public RedBus search flow:

```bash
mvn test
```

Allure result files are written to `target/allure-results`.

Local `mvn test` runs do not automatically trigger the CI reporting/email pipeline.

## Viewing Allure reports

Generate the HTML report:

```bash
mvn allure:report
```

Open the generated report locally:

```bash
mvn allure:serve
```

## CI reporting and email notifications

The repository includes a CI-only reporting path that can send an SMTP summary email after test execution. In CI, the listener writes the execution summary, Maven generates the Allure report, the mailer sends the email with configured artifacts attached, and then a build guard fails the Maven run if there were test failures.

To run that path explicitly outside CI, use:

```bash
mvn test -Pci-reporting
```

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
mvn test -Pci-reporting -Dsmtp.enabled=true -Dsmtp.host=smtp.gmail.com -Dsmtp.port=587 -Dsmtp.username=your-user@gmail.com -Dsmtp.password=your-app-password -Dsmtp.from=your-user@gmail.com -Dsmtp.to=qa-team@example.com,dev-team@example.com
```

By default the email includes:

- suite and test names
- start and finish times
- passed, failed, skipped, and total counts
- the TestNG `emailable-report.html` as an attachment when available
- a zipped generated Allure report artifact

Optional artifact behavior:

- `smtp.attach.raw.allure.results=true` adds the raw `allure-results.zip` attachment
- if this flag is omitted or set to `false`, raw Allure results are not emailed

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

- Browser drivers are auto-managed by default using WebDriverManager, which resolves a compatible driver version at runtime.
- SLF4J now routes to Log4j2 through the Maven dependency graph, so new logging code can use the SLF4J API directly.
- Framework logs are intentionally written to help debugging without logging passwords, SMTP secrets, or raw report contents.
- If you want to force a specific local binary instead, set `driver.chrome.path`, `driver.edge.path`, `driver.firefox.path`, or `driver.ie.path`.
- You can disable auto-management with `driver.auto.manage=false`.
- The active Eclipse classpath no longer depends on the deleted `jarfiles/` folder.
- If required app-specific values are missing, those tests are skipped instead of failing for configuration reasons.

