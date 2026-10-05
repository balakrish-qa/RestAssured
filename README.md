# RestAssured API Automation Framework

## Overview

This project is a REST API automation framework built using **Java, Rest
Assured, Cucumber, JUnit, and TestNG**.

The framework automates GitHub Repository API scenarios using CRUD-style
operations:

-   Create a repository
-   Read/list repositories
-   Update repository topics
-   Delete a repository

The API under test is the **GitHub REST API**:

`https://api.github.com`

## Tech Stack

  Technology         Version / Usage
  ------------------ ---------------------------------
  Java               17
  Maven              Build and dependency management
  Rest Assured       5.3.1
  Cucumber           7.11.0
  JUnit 5            5.7.1
  TestNG             7.8.0
  Jackson Databind   2.11.0
  JSON Simple        1.1.1
  org.json           20200518
  Hamcrest           2.2

## Project Structure

``` text
RestAssured/
│
├── pom.xml
├── README.md
├── .gitignore
│
├── src/
│   ├── main/
│   │   └── resources/
│   │       ├── Create_Repository.json
│   │       └── Update_Repo_Topics.json
│   │
│   └── test/
│       ├── java/
│       │   └── com/RestAssured/
│       │       ├── RequestMethods.java
│       │       ├── Runner.java
│       │       └── stepDef/
│       │           ├── RequestMethodsForGitHub.java
│       │           └── StepDefinitions.java
│       │
│       └── resources/
│           └── com/RestAssured/features/
│               └── githubRepo.feature
```

## Framework Components

### 1. Feature File

`src/test/resources/com/RestAssured/features/githubRepo.feature`

Contains the BDD scenarios for GitHub repository operations.

The scenarios cover:

-   Create repository --- validates HTTP `201`
-   Get/list repositories --- validates HTTP `200`
-   Verify repository name from response
-   Update repository topics --- validates HTTP `200`
-   Delete repository --- validates HTTP `204`

### 2. Step Definitions

`StepDefinitions.java`

Maps the Cucumber Gherkin steps to Java methods and stores the API
response for validation.

### 3. API Request Methods

`RequestMethodsForGitHub.java`

Contains the reusable Rest Assured methods for:

-   Creating a repository
-   Reading repositories
-   Updating repository topics
-   Deleting a repository
-   Validating repository information from the response

### 4. Test Runner

`Runner.java`

Uses Cucumber with JUnit to execute the feature file and connect the
feature steps to the step-definition package.

### 5. Request Payloads

The request bodies are maintained as JSON files under:

`src/main/resources/`

-   `Create_Repository.json` --- repository creation payload
-   `Update_Repo_Topics.json` --- repository topics payload

## Prerequisites

Install the following before running the project:

1.  Java JDK 17
2.  Maven
3.  IntelliJ IDEA or another Java IDE
4.  A GitHub account
5.  A GitHub Personal Access Token with the required repository
    permissions

Verify Java:

``` bash
java -version
```

Verify Maven:

``` bash
mvn -version
```

## Configuration

The current implementation uses a GitHub Bearer token directly in the
Java source.

**For security, do not commit a real GitHub token to GitHub.**

Before sharing this project:

-   Revoke/rotate any token that has previously been exposed.
-   Store the token as an environment variable or other secure
    configuration.
-   Update the repository/user names used by the API requests for the
    assessment account.

The current source contains repository/user values in the request paths,
so these should be changed to the test account/repository being used.

## How to Run

### Using IntelliJ IDEA

1.  Open the project in IntelliJ IDEA.
2.  Open the Maven panel.
3.  Select:

`Lifecycle → test`

4.  Double-click `test`.

Alternatively, open the `Runner.java` class and run it as a JUnit test.

### Using Terminal

From the project root directory:

``` bash
mvn test
```

For a clean test execution:

``` bash
mvn clean test
```

If Maven is not configured in the system PATH, the test can also be
executed from IntelliJ's Maven panel.

## API Scenarios

### Create Repository

Endpoint:

``` text
POST /user/repos
```

Expected response:

``` text
201 Created
```

Request payload is maintained in:

``` text
src/main/resources/Create_Repository.json
```

### List Repositories

Endpoint:

``` text
GET /user/repos
```

Expected response:

``` text
200 OK
```

The framework also verifies the repository name from the response body.

### Update Repository Topics

Endpoint:

``` text
PUT /repos/{owner}/{repo}/topics
```

Expected response:

``` text
200 OK
```

Request payload is maintained in:

``` text
src/main/resources/Update_Repo_Topics.json
```

### Delete Repository

Endpoint:

``` text
DELETE /repos/{owner}/{repo}
```

Expected response:

``` text
204 No Content
```

## Validation Approach

The framework validates API responses using:

-   HTTP status-code assertions
-   Response-body validation
-   Repository name validation

Examples of expected status codes:

``` text
Create  → 201
Read    → 200
Update  → 200
Delete  → 204
```

## Reports and Generated Files

Maven/TestNG/Cucumber can generate test output during execution.

Generated directories such as:

``` text
target/
test-output/
```

should not be committed to the Git repository.

They can be regenerated by running the tests again.

## GitHub Submission

The repository should contain the source and configuration required to
build and execute the framework.

Recommended files to commit:

``` text
pom.xml
README.md
.gitignore
src/
```

Do not commit:

``` text
target/
test-output/
.idea/
.settings/
.classpath
.project
*.iml
```

## Notes

The framework is designed around reusable request methods, Cucumber BDD
feature files, step definitions, and external JSON request payloads.

For production-level use, authentication tokens and environment-specific
values should be externalized instead of being hardcoded in Java source
files.
