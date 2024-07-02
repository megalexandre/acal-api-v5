Feature: find by id

  Scenario: find id when is existing
    Given database has category with id "01HQ34HQ2X27955TQ5YBJSVAC9"
    When I find category by id "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then the status response should be 200

  Scenario: find id when not exist
    Given database has no data
    When I find category by id "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then the status response should be 204
