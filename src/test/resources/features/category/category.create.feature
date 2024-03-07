Feature: creating a category

  Scenario: creating a new category

    when we save a CATEGORY, the response must be CREATED/201
    and there must be a record in the database

    When database is clean
    Given a category with name "socio" and type "EFFECTIVE" is send by post
    Then the status response should be 201
    And the database need has a "category" with returned id

  Scenario: trying create a duplicated category

    A duplicate attempt to save a category must result in a BAD_REQUEST/400
    and only one category can be registered

    When database is clean
    Given a category with name "socio" and type "EFFECTIVE" is send by post
    And a category with name "socio" and type "EFFECTIVE" is send by post
    Then the status response should be 400
    And database the size of the "category" document should be 1

  Scenario: creating a category with two values

    when a category sent with two or more values,
    the saved document must have the sum of the values

    When database is clean
    Given a category with water value of 20 and category value of 30 is sent
    Then there must be a saved category with a name value of 50
