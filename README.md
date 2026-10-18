# Password Validator

A small Java project that demonstrates clean validation logic and automated testing with TestNG and Maven.

## Overview

The `PasswordValidator` class checks whether a password satisfies four rules:

- At least 8 characters long
- Contains at least one digit
- Contains at least one uppercase letter
- Contains no spaces

Invalid input raises a custom `InvalidPasswordException`. The validator uses small helper methods to keep each rule easy to understand and test.

## Tech Stack

- Java 17
- Maven
- TestNG

## Prerequisites

Install Java 17 or a compatible JDK and Maven. Verify your setup with:

```bash
java -version
mvn -version
```

## Run the Tests

Clone the repository, open a terminal in the project directory, and run:

```bash
mvn clean test
```

Maven compiles the project and executes the TestNG tests.

Test reports are generated under `target/surefire-reports/`.

## Test Organization

Tests are organized into two TestNG groups:

- **Smoke:** Checks the primary valid-password scenario.
- **Regression:** Checks validation rules, boundary values, and invalid inputs.

A `@DataProvider` supplies multiple password examples to a single test method. Separate tests verify that invalid inputs throw `InvalidPasswordException`. A custom `ITestListener` logs when tests start, pass, or fail.

The test cases cover:

- Valid password examples
- Minimum-length boundaries: below, at, and above 8 characters
- Missing digits
- Missing uppercase letters
- Spaces in passwords
- Null input

The data-driven cases and standalone tests intentionally overlap in places: the former checks expected validation outcomes, while the latter explicitly checks exception behavior.

## What I Learned

- Designing small, focused Java methods
- Implementing custom runtime exceptions
- Writing data-driven tests with TestNG `@DataProvider`
- Using `expectedExceptions` to test invalid input
- Grouping tests into smoke and regression suites
- Logging test lifecycle events with `ITestListener`
- Applying equivalence partitioning and boundary value analysis
- Running tests and reviewing Maven Surefire reports
- Using Git commits to track changes incrementally

## Project Goal

This project is a learning exercise in writing maintainable validation code and building a reliable automated test suite.
