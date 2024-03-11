Feature: find invoice proposal for reference

  Scenario: Shouldn't return any link inactive

    when all links is inactive we can't return anything

    Given database has inactive link with invoice for reference "2025.01"

    When I find invoice proposal by reference "2025.01"

    Then the status response should be 200
    And the response should has 0 invoices


  Scenario: Shouldn't return link if him has invoice for reference

    Given database has active link with invoice for reference "2025.01"
    When I find invoice proposal by reference "2025.01"
    Then the status response should be 200
    And the response should has 0 invoices

  Scenario: finding invoice proposal for reference

    when we send a reference should, response with all active link's
    that's have no invoice for that reference

    Given database is clean
    Given database has active link with invoice for reference "2025.01"
    Given database has inactive link with invoice for reference "2025.01"
    Given database has two active in different areas link without invoice for reference "2025.01"

    When I find invoice proposal by reference "2025.01"

    Then the status response should be 200
    And the response should has 2 invoices
    And the response should has two areas
    And total value should be 40

  Scenario: finding invoice when all link has invoice for reference

    when we find proposal, but all links has invoice for reference
    should return empty

    Given database is clean
    Given database has active link with invoice for reference "2025.01"
    When I find invoice proposal by reference "2025.01"
    Then the status response should be 200