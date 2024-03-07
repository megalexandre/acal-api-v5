Feature: creating a category

  Scenario: creating a new category
    When database is clean
    Given a category with name "socio" and type "EFFECTIVE" is send by post
    Then the status response should be 201

  #Scenario: creating a new category
  #  When o banco de dados está vazio
  #  And uma categoria como nome "socio" e tipo "EFFECTIVE" é salva
  #  Then o status de resposta deve ser 201
  #  And deve haver uma categoria salva com nome "socio" e tipo "EFFECTIVE"

  #Scenario: criando uma categoria duplicada
  #  When o banco de dados está vazio
  #  When uma categoria como nome "socio" e tipo "EFFECTIVE" é salva
  #  And  uma categoria como nome "socio" e tipo "EFFECTIVE" é salva
  #  Then o status de resposta deve ser 400
  #  And deve haver uma categoria salva com nome "socio" e tipo "EFFECTIVE"

  #Scenario: criando uma categoria com dois valores
  #  When o banco de dados está vazio
  #  When uma categoria com valor de agua de 20 e valor de categoria igual a 30 é salva
  #  Then deve haver uma categoria salva com nome valor de 50

  #Scenario: criando uma categoria sem valores
  #  When o banco de dados está vazio
  #  When uma categoria sem valores é enviada
  #  Then o status de resposta deve ser 400
  #  And o banco de dados deve estar vazio

  #Scenario: criando uma categoria com valores negativos
  #  When o banco de dados está vazio
  #  When uma categoria com valores negativos é enviada
  #  Then o status de resposta deve ser 400
  #  And o banco de dados deve estar vazio