# language: pt

  Funcionalidade: criação de novos usuarios
    cadastro de uma nova categoria
    deve ser possivel criar uma nova categoria, desde de não tenha o mesmo nome e
    mesmo type de uma já existente

  Cenário: criar uma categoria
    Quando : faco um post para /category
    """json
    {
      "name" : "SOCIO EFETIVO",
      "type" : "TEMPORARY",
      "values":
      [
        {
          "name":"water",
          "value": 0.00
        },
        {
          "name":"partnership",
          "value": 3.00
        }
      ]
    }
    """
    Então : a resposta deve conter ser um Ok 200 com o ID da nova categoria
