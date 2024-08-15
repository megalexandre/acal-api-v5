Feature: create link

  Background:
    Given database is clean

  Scenario: creating a new link

    to create a link in necessary has a category, an address and a customer.

    Given database has customer id "01J1R0WCA3TMKYA3CNPWR9HC15"
    And database has category id "01J5B7HCVYBNW012D25BPAEVXW"
    And database has address id "01J1R1MSRFHNVPC65B6JXYBH6A"

    When a "link" is send by post
    """
    {
      "customerId": "01J1R0WCA3TMKYA3CNPWR9HC15",
      "categoryId": "01J5B7HCVYBNW012D25BPAEVXW",
      "addressId": "01J1R1MSRFHNVPC65B6JXYBH6A"
    }
    """

    Then the status response should be 201
    And the database need has a "link" with returned id
