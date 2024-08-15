Feature: create link

  Background:
    Given database is clean

  Scenario: creating a new link with invalid id

    to create a link in necessary has a category, an address and a customer.

    Given a link category "1" and customer "1" and address "1" is send by post

    Then the status response should be 400
