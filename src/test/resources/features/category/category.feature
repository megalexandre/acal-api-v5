Feature: Testing Category API

  Scenario: Getting a category
    When I send a GET request to "category"
    Then the response status code should be 200

  Scenario: Saving a category
    When I send a POST request to "category"
    """
      {
        "name": "Test Category",
        "type": "EFFECTIVE",
        "values": [
          {"name": "Value1", "value": 10.0},
          {"name": "Value2", "value": 20.0}
        ]
      }
      """
    Then the response status code should be 201
