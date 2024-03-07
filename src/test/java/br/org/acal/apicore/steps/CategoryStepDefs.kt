package br.org.acal.apicore.steps

import br.org.acal.apicore.common.enums.CategoryType
import io.cucumber.java.en.Given
import stub.categoryCreateStub

class CategoryStepDefs: RestStepDefs() {

    @Given("a category with name {string} and type {string} is send by post")
    fun aCategoryWithNameAndTypeIsSendByPost(name: String, type: String) {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                name = name,
                type = CategoryType.valueOf(type),
            )
        ))
    }

    /*
    @And("uma categoria como nome {string} e tipo {string} é salva")
    fun oneCategoryWithNameAndTypeIsSaved(name: String, type: String) {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                name = name,
                type = CategoryType.valueOf(type),
            )
        ))
    }

    @And("deve haver uma categoria salva com nome {string} e tipo {string}")
    fun deveHaverUmaCategoriaSalvaComNomeETipo(name: String, type: String) {
        categoryRepository.findAll().first().let {
            assertEquals(name, it.name)
            assertEquals(type, it.type.name)
        }
    }

    @When("uma categoria com valor de agua de {int} e valor de categoria igual a {int} é salva")
    fun umaCategoriaComValorDeAguaDeEValorDeCategoriaIgualA(waterValue: Int, categoryValue: Int) {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                values = listOf(
                    CategoryCreateValuesRequest(
                        name = "Water",
                        value = BigDecimal(waterValue)
                    ),
                    CategoryCreateValuesRequest(
                        name = "partnership",
                        value = BigDecimal(categoryValue)
                    )
                )
            )
        ))
    }

    @Then("deve haver uma categoria salva com nome valor de {int}")
    fun deveHaverUmaCategoriaSalvaComNomeValorDe(value: Int) {
        categoryRepository.findAll().first().let {
            assertEquals(BigDecimal(value).intValueExact(), it.toEntity().total.intValueExact())
        }
    }

    @When("uma categoria sem valores é enviada")
    fun umaCategoriaSemValoresEEnviada() {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                values = emptyList()
            )
        ))
    }

    @And("o banco de dados deve estar vazio")
    fun oBancoDeDadosDeveEstarVazio() {
        assertEquals(0, categoryRepository.findAll().size)
    }

    @When("uma categoria com valores negativos é enviada")
    fun umaCategoriaComValoresNegativosEEnviada() {
        stepShared.response = executePost("category", gson.toJson(
            categoryCreateStub.copy(
                values = listOf(
                    CategoryCreateValuesRequest(
                        name = "Water",
                        value = BigDecimal(-10)
                    ),
                    CategoryCreateValuesRequest(
                        name = "partnership",
                        value = BigDecimal(-10)
                    )
                )
            )
        ))
    }

    @Given("o banco de possui uma categoria com id {string}")
    fun oBancoDePossuiUmaCategoriaComId(id: String) {
        categoryRepository.save(
            categoryDocumentStub.copy(
                id = id
            )
        )
    }

    @When("eu busco uma categoria por id {string}")
    fun euBuscoUmaCategoriaPorId(id: String) {
        stepShared.response = executeGet("category/$id")
    }

    @Given("o banco de dados possui tres categoria sendo uma de cada tipo")
    fun oBancoDeDadosPossuiTresCategoriaSendoUmaDeCadaTipo() {
        categoryRepository.deleteAll()
        categoryRepository.saveAll(listOfCategoryDocumentFromAllTypesStub)
    }

    @When("eu filtro sem nenhum parametros")
    fun euFiltroSemNenhumParametros() {
        stepShared.response = executeGet("category/find")
    }

    @When("eu filtro por tipo {string}")
    fun euFiltroPorTipo(type: String) {
        stepShared.response = executeGet("category/find", mapOf(
            "type" to type
        ))
    }
    @When("eu filtro por nome {string}")
    fun euFiltroPorNome(name: String) {
        stepShared.response = executeGet("category/find", mapOf(
            "name" to name
        ))
    }

    @And("o tamanho da lista de resposta deve ser {int}")
    fun oTamanhoDaListaDeRespostaDeveSer(size: Int) {

        val data: List<CategoryFindResponse> = gson.fromJson(
            stepShared.response?.body?.asString(),
            Array<CategoryFindResponse>::class.java).asList()

        assertEquals(size, data.size)
    }


    @Given("o banco de dados possui tres categoria como residente, restaurante e temporario")
    fun oBancoDeDadosPossuiTresCategoriaComoResidenteRestauranteETemporario() {
        categoryRepository.deleteAll()
        categoryRepository.saveAll(
            listOf(
                categoryDocumentStub.copy(
                    id = random(),
                    name = "residente",
                    type = EFFECTIVE
                ),
                categoryDocumentStub.copy(
                    id = random(),
                    name = "restaurante",
                    type = FOUNDING
                ),
                categoryDocumentStub.copy(
                    id = random(),
                    name = "temporario",
                    type = TEMPORARY
                )
            )
        )
    }
    */

}