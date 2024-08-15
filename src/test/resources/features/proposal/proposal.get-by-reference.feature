Feature: find invoices proposal

  Background:
    Given database is clean

  Scenario: when all links is inactive shouldn't return link

    when all links is inactive we can't return anything

    Given database has inactive link with invoice for reference "2025.01"
    When I find invoice proposal by reference "2025.01"
    Then the status response should be 200
    And the response should has 0 invoices

  Scenario: Shouldn't return link if him has invoice for reference

    when links already has invoice with reference can't return anything

    Given database has active link with invoice for reference "2025.01"
    When I find invoice proposal by reference "2025.01"
    Then the status response should be 200
    And the response should has 0 invoices

  Scenario: finding invoice proposal for reference

    when we send a reference should, response with all active link's
    that's have no invoice for that reference

    Given database has active link with invoice for reference "2025.01"
    Given database has inactive link with invoice for reference "2025.01"
    Given database has two active in different areas link without invoice for reference "2025.01"

    When I find invoice proposal by reference "2025.01"

    Then the status response should be 200
    And the response should has 2 invoices
    And total value should be 40

  Scenario: find invoices in different areas

    when whe find invoices in many areas
    we need return same number of areas

    Given database has two active in different areas link without invoice for reference "2025.01"
    When I find invoice proposal by reference "2025.01"
    Then the status response should be 200
    And the response should has 2 areas