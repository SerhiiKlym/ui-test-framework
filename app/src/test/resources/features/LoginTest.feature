Feature: User Login

  Scenario Outline: Unsuccessful login with empty credentials
    Given User is on the login page
    When User enters username "<name>"
    And User enters password for user "<name>"
    And User clears username
    And User clears password
    And User clicks on login button
    Then User should be shown an error message 'no username'

    Examples:
      | name                    |
      | standard_user           |
      | locked_out_user         |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |


  Scenario Outline: Unsuccessful login with empty password
    Given User is on the login page
    When User enters username "<name>"
    And User enters password for user "<name>"
    And User clears password
    And User clicks on login button
    Then User should be shown an error message 'no password'

    Examples:
      | name                    |
      | standard_user           |
      | locked_out_user         |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |


  Scenario Outline: Successful login with
    Given User is on the login page
    When User enters username "<name>"
    And User enters password for user "<name>"
    And User clicks on login button
    Then User should be redirected to inventory page

    Examples:
      | name                    |
      | standard_user           |
      | locked_out_user         |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |