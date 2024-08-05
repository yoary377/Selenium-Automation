# Project Name: UI Automation Testing

## Overview

This project automates web testing for the XM trading platform using Selenium WebDriver. The project is structured using the Page Object Model (POM) design pattern to enhance test maintenance and reduce code duplication.

The tests are designed to cover various functionalities of the web application, focusing on navigation, form submissions, and data validation.

## Getting Started

### Prerequisites

Before running the tests, ensure the following are installed:
- Java JDK 8 or higher
- Gradle
- Google Chrome browser
- ChromeDriver compatible with the installed version of Chrome

### Installation

1. Clone the repository:

## Running Tests

Tests are executed using Gradle. You can run tests directly from the command line.

### Running Tests

To execute Desktop version of the test:

`./gradlew test`

Tests support 3 resolutions:
- 800x600
- `./gradlew test -Presolution=800x600`
- 1024x768
- `./gradlew test -Presolution=1024x768`
- maximum
- `./gradlew test`


## Reporting

Test results are automatically generated in the `build/reports/tests/test` directory. You can open the `index.html` file in any web browser to view a detailed report of the test executions.

## Errors
If you encounter errors with the driver version, uncomment the 
``//WebDriverManager.chromedriver().clearDriverCache().setup();``
in the BaseTest class
