# EPAM Automated Testing in JAVA #5 - Final Task
A Selenium TestNG project based on Gradle, final task for Epam Automated Testing in JAVA #5


## Task description

Automate the following script:

1. Launch URL: https://www.saucedemo.com/
* UC-1 Test Login form with empty credentials:
* Type any credentials into "Username" and "Password" fields.
* Clear the inputs.
* Hit the "Login" button.
* Check the error messages: "Username is required".

2. UC-2 Test Login form with credentials by passing Username:
* Type any credentials in username.
* Enter password.
* Clear the "Password" input.
* Hit the "Login" button.
* Check the error messages: "Password is required".

3. UC-3 Test Login form with credentials by passing Username & Password:
* Type credentials in username which are under Accepted username are sections.
* Enter password as secret sauce.
* Click on Login and validate the title “Swag Labs” in the dashboard.
* Provide parallel execution, add logging for tests and use Data Provider to parametrize tests. Make sure that all tasks are supported by these 3 conditions: UC-1; UC-2; UC-3.
* Please, add task description as README.md into your solution!

## To perform the task use the various of additional options:

* Test Automation tool: Selenium WebDriver;
* Project Builder: ~~Maven~~ Gradle;
* Browsers:
  1) Firefox; 
  2) Chrome;
* Locators: CSS;
* Test Runner: TestNG;
* Assertions: AssertJ;

[Optional] Patterns: 
  1) Singleton;
  2) Adapter;
  3) Strategy;

[Optional] Test automation approach: BDD;

[Optional] Loggers: SLF4J.
