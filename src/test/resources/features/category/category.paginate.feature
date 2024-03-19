Feature: paginate category

  Scenario: when I find category/paginate without params should return all data until default page
    Given the base has three categories one be type
    When i query paginate
    Then the status response should be 200
    And the response content should has 3 elements

