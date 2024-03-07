#language pt

Feature: consultando um conta

  Scenario: consultando uma conta pré-existente
    Given o banco possui uma conta com id "01HQ34HQ2X27955TQ5YBJSVAC9" e linkId "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then eu consulto uma conta com id "01HQ34HQ2X27955TQ5YBJSVAC9"
    And o status de de resposta deve ser 200