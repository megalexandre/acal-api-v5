Feature: creating a address

  Background:
    database is clean

  Scenario: creating a address without area

    When a address without area is send by post
    Then the status response should be 400
    And and response has "message" equal to "Can't possible create an Address without an Area"
    And database the size of the "address" document should be 0

  Scenario: creating a address without number

    When a address without number is send by post
    Then the status response should be 400
    And and response has "message" equal to "Can't possible create an Address without a Number"
    And database the size of the "address" document should be 0

  Scenario: creating a duplicate address

    address has a composed-key with area and number, we can't duplicate it

    Given database has a address with number "1" and areaId "01HQ34HQ2X27955TQ5YBJSVAC9"
    When a "address" is send by post
      """
      {
        "area": {
          "id": "01HQ34HQ2X27955TQ5YBJSVAC9",
          "name":"Avenida Fernando Daltro"
        },
        "number": "1",
        "hasHydrometer": true,
        "letter": "A"
      }
      """
    Then the status response should be 400
    And and response has "message" equal to "Already exists an address with this number: 1 and area Avenida Fernando Daltro"
    And database the size of the "address" document should be 1