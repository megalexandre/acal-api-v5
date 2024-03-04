Feature: buscar categorias por id

  Scenario: consultando um categoria por uma id que existe
    Given o banco de possui uma categoria com id "01HQ34HQ2X27955TQ5YBJSVAC9"
    When eu busco uma categoria por id "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then o status de de resposta deve ser 200

  Scenario: consultando um categoria por uma id que nao existe
    Given o banco de dados está vazio
    When eu busco uma categoria por id "01HQ34HQ2X27955TQ5YBJSVAC9"
    Then o status de de resposta deve ser 204

  Scenario: consultando um categoria por uma id que não é valido
    When eu busco uma categoria por id "a"
    Then o status de de resposta deve ser 400

