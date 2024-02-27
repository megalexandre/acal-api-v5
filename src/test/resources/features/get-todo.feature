Feature: Testing Todo API

  Scenario: Retrieve Todo
    When I send a GET request to "/todo"
    Then the response status code should be 200