Feature: criando uma categoria

  Scenario: criando uma cateria nova
    When o banco de dados está vazio
    And uma categoria como nome "socio" e tipo "EFFECTIVE" é salva
    Then o status de de resposta deve ser 201
    And deve haver uma categoria salva com nome "socio" e tipo "EFFECTIVE"
