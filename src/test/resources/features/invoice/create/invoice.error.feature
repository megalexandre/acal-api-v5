Feature: creating a invoice

  Background:
    Given database is clean

  Scenario: creating a invoice with duplicated reference

    if the link have a invoice for sent reference, we go throw exception

    Given database a link with Id "01HQ34HQ2XB9ZGYHHX1XA4ANCG"
    And link has a invoice with linkId = "01HQ34HQ2XB9ZGYHHX1XA4ANCG" and reference "2024.10"
    When I send a post to "invoice"
    """
    {
      "linkId": "01HQ34HQ2XB9ZGYHHX1XA4ANCG",
      "reference": "2024.10",
      "invoiceNumber": "2000.01.2",
      "emission": "2024-08-18T14:30:00",
      "dueDate": "2024-08-25",
      "invoiceDetails": [
        {
          "reason": "CATEGORY",
          "value": 150.00
        },
        {
          "reason": "WATER",
          "value": 25.00
        }
      ]
    }
    """
    Then the status response should be 400
    And and response has "message" equal to "Already exists a invoice with this reference: Reference(year=2024, month=OCTOBER)}"


  Scenario: creating a invoice without details

    can't be possible create a invoice without details

    Given database a link with Id "01HQ34HQ2XB9ZGYHHX1XA4ANCG"
    When I send a post to "invoice"
    """
    {
      "linkId": "01HQ34HQ2XB9ZGYHHX1XA4ANCG",
      "reference": "2024.10",
      "invoiceNumber": "2000.01.2",
      "emission": "2024-08-18T14:30:00",
      "dueDate": "2024-08-25",
      "invoiceDetails": []
    }
    """
    Then the status response should be 400
    And and response has "message" equal to "Can't create a invoice without details"


