Feature: creating a address

  Background:
    Given database is clean

  Scenario: creating a address with minimum data

    to create a address it is necessary at least an area and a number

    Given database has an area with id "01HQ34HQ2X27955TQ5YBJSVAC9"
    When an address with area "01HQ34HQ2X27955TQ5YBJSVAC9" and number "1" is send by post
    Then the status response should be 201
    And the database need has a "address" with returned id


  Scenario: creating a address with full data

    address can have beyond the minimum data hasHydrometer and Letter

    Given database has an area with id "01HQ34HQ2X27955TQ5YBJSVAC9"
    When an address with the following data is sent by POST
      """
      {
        "area": {
          "id": "01HQ34HQ2X27955TQ5YBJSVAC9",
          "name":"area"
        },
        "number": "1",
        "hasHydrometer": true,
        "letter": "A"
      }
      """
    Then the status response should be 201
    And the database need has a "address" with returned id
    And the database needs to have an address with hasHydrometer "true" and letter "A"