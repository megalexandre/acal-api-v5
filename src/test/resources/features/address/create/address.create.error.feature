Feature: creating a address

  Background:
    database is clean

  Scenario: creating a address without area

    Given a address without area is send by post
    Then the status response should be 400
    And and response has "message" equal to "Can't possible create an Address without an Area"
    And database the size of the "address" document should be 0

  Scenario: creating a address without number

    Given a address without number is send by post
    Then the status response should be 400
    And and response has "message" equal to "Can't possible create an Address without a Number"
    And database the size of the "address" document should be 0
