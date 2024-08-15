Feature: create link

  Background:
    Given database is clean

  Scenario: creating a new link

    to create a link in necessary has a category, a address and a customer.

    When database has customer id "1"
    And database has category id "1"
    And database has address id "1"

    Given a link category "1" and customer "1" and address "1" is send by post

    Then the status response should be 201
    And the database need has a "link" with returned id
