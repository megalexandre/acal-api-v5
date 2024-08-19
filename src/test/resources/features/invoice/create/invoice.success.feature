Feature: creating a invoice

  Background:
    Given database is clean

  Scenario: creating a invoice

    to create a invoice it's necessary a link without a invoice for that reference

    Given database a link with Id "01HQ34HQ2XB9ZGYHHX1XA4ANCG"
    When I send a post to "invoice"
    """
    {
      "linkId": "01HQ34HQ2XB9ZGYHHX1XA4ANCG",
      "reference": "2024.10",
      "invoiceNumber": "2000.01.1",
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
    Then the status response should be 201
