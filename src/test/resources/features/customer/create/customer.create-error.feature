Feature: creating a customer

  Background:
    database is clean

  Scenario: create a customer with minimum data

    to create a customer we need at least a name and a document number

    When a customer is send by post
       """
      {
        "name": "alexandre",
        "document": "54734064083"
      }
      """
    Then the status response should be 201
    And the database need has a "customer" with returned id
    And the database needs to have an customer with name "alexandre" and document "54734064083"
