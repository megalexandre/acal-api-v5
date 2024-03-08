Feature: find invoice proposal for reference

  when we send a reference should response with all active link's
  that's have no invoice for that reference

  Scenario: finding invoice proposal for reference

    Given database is clean
    Given database has active link with invoice for reference "2025.01"
    Given database has inactive link with invoice for reference "2025.01"
    Given database has two active link without invoice for reference "2025.01"

    When I find invoice proposal by reference "2025.01"

    Then the status response should be 200
    And the response should has 2 invoices


