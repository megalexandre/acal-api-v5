Feature: find invoice

  Scenario: finding invoice
    Given database has invoice with id "01HQ34HQ2X27955TQ5YBJSVAC9" and linkId "01HQ34HQ2XB9ZGYHHX1XA4ANCG"
    When I find invoice by "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then the status response should be 200

  Scenario: finding nonexistent invoice
    Given database is clean
    When I find invoice by "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then the status response should be 204