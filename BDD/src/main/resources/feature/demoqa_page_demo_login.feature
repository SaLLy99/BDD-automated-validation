Feature: DemoQa Authorization
  Scenario: Check login is successfull with valid credentials
    Given browser is open
    When user is on login page
    Then users enters username and password
    Then user is navigated to the home page