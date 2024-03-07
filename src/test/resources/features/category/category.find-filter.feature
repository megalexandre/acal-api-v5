#Feature: buscar categorias por filtro

  #Scenario: filtrando categoria por um filtro sem resultado
  #  Given o banco de dados possui tres categoria sendo uma de cada tipo
  #  When eu filtro por nome "NAO_EXISTE_ESSE_DADO"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 0

  #Scenario: filtrando categoria por tipo com tres registros
  #  Given o banco de dados possui tres categoria sendo uma de cada tipo
  #  When eu filtro sem nenhum parametros
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 3

  #Scenario: filtrando categoria por tipo Sócio Fundador
  #  Given o banco de dados possui tres categoria sendo uma de cada tipo
  #  When eu filtro por tipo "Sócio Fundador"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 1

  #Scenario: filtrando categoria por tipo Sócio Efetivo
  #  Given o banco de dados possui tres categoria sendo uma de cada tipo
  #  When eu filtro por tipo "Sócio Efetivo"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 1

  #Scenario: filtrando categoria por tipo Temporário
  #  Given o banco de dados possui tres categoria sendo uma de cada tipo
  #  When eu filtro por tipo "Temporário"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 1

  #Scenario: filtrando categoria por nome residente
  #  Given o banco de dados possui tres categoria como residente, restaurante e temporario
  #  When eu filtro por nome "residente"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 1

  #Scenario: filtrando categoria por nome restaurante
  #  Given o banco de dados possui tres categoria como residente, restaurante e temporario
  #  When eu filtro por nome "restaurante"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 1

  #Scenario: filtrando categoria por nome temporario
  #  Given o banco de dados possui tres categoria como residente, restaurante e temporario
  #  When eu filtro por nome "temporario"
  #  Then o status de de resposta deve ser 200
  #  And o tamanho da lista de resposta deve ser 1
