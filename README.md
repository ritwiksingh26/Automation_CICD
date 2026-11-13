# Selenium + Cucumber E-commerce Automation (CI with Jenkins)

UI test automation for an e-commerce practice site's login → add to cart → checkout → order confirmation flow, built with Selenium WebDriver, TestNG and Cucumber BDD, and run from a Jenkins job.

## Features

- **Page Object Model** — `LandingPage`, `ProductCatalogue`, `CartPage`, `CheckoutPage`, `ConfirmationPage`, `OrderPage`, sharing an `AbstractComponents` base for waits and navigation
- **BDD with Cucumber** — readable feature files (`StandAloneTest.feature`, `ErrorValidations.feature`) with step definitions, run through TestNG
- **Data-driven** — test data loaded from JSON with Jackson
- **Cross-browser** — Chrome, Firefox and headless Chrome, chosen with `-Dbrowser=`
- **Reliability & reporting** — TestNG listeners, a retry analyzer for flaky tests, screenshots on failure, Extent Reports
- **CI** — Jenkins job runs the suite with Maven

## Tech stack

Java · Maven · Selenium 4 · TestNG · Cucumber 7 · WebDriverManager · Jackson · Extent Reports · Jenkins

## Running the tests

```bash
mvn test                                  # TestNG suite (testng.xml)
mvn test -Dbrowser=firefox                # another browser
mvn test -Dbrowser=chromeheadless         # headless
mvn test -Dcucumber.filter.tags=@Regression   # Cucumber scenarios by tag
```

The Extent report is written to `ExtentreportsDemo/index.html` and failure screenshots to `reports/`.
