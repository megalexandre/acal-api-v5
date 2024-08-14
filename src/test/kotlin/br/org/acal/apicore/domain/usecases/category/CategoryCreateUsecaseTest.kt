package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import stub.categoryStub

class CategoryCreateUsecaseTest{

    private val dataSource: CategoryDataSource = mockk()
    private val usecase: CategoryCreateUsecase = CategoryCreateUsecase(dataSource = dataSource)

    @Test
    fun whenCategoryIsNotAlreadySaveShouldSave() {
        val category = categoryStub
        every { dataSource.findByNameAndType(any(),any()) } returns null
        every { dataSource.save(category) } returns category

        usecase.execute(category)

        verify { dataSource.save(category) }
    }
    @Test
    fun whenCategoryIsAlreadySaveShouldThrowException() {
        val category = categoryStub
        every { dataSource.findByNameAndType(name = category.name, type = category.type) } returns listOf(categoryStub)

        val error = assertThrows<InvalidUsecaseException> {
            usecase.execute(category)
        }

        assertEquals("already exists a category with this name: ${category.name} and type ${category.type}",error.message )

    }
}
