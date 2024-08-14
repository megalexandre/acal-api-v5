Feature: creating a address

  Scenario: creating a address without an area

    When database is clean
    Given a address without area is send by post
    Then the status response should be 400
    And and response has "message" equal to "Can't create an Address with an Area"
    And database the size of the "address" document should be 0
