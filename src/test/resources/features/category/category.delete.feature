Feature: delete categor

  Scenario: deleting existent category

    Given database has category with id "01HQ34HQ2X27955TQ5YBJSVAC9"
    When delete category by "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then the status response should be 200
    And database should has none category

  Scenario: deleting a not valid category

    Given database has no data
    When delete category by "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then the status response should be 200
    And database should has none category