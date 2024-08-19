Feature: create link

  Background:
    Given database is clean

  Scenario: creating a new link with invalid id

    if an id is invalid does not accepet them

    When a "link" is send by post
    """
    {
      "customerId": "1",
      "categoryId": "01J5B7HCVYBNW012D25BPAEVXW",
      "addressId": "01J1R1MSRFHNVPC65B6JXYBH6A"
    }
    """
    Then the status response should be 401
    And database the size of the "link" document should be 0
