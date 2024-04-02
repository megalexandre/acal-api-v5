package br.org.acal.apicore.domain.usecases.category

import br.org.acal.apicore.domain.datasource.CategoryDataSource
import br.org.acal.apicore.infrastructure.exception.InvalidUsecaseException
import io.azam.ulidj.ULID
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import stub.categoryStub

class CategoryUpdateUsecaseTest{

    private val dataSource: CategoryDataSource = mockk()

    private val usecase: CategoryUpdateUsecase = CategoryUpdateUsecase(dataSource = dataSource)

    @Test
    fun whenCategoryDoesNotExistsShouldNotSave(){
        val category = categoryStub

        every { dataSource.findByNameAndType(name = category.name, type = category.type) } returns null

        val error = assertThrows<InvalidUsecaseException> {
            usecase.execute(category)
        }

        assertEquals("Category can't be updated because does not exists.", error.message)
        verify(exactly = 0) { dataSource.save(any()) }
    }

    @Test
    fun whenCategoryExistsShouldDoNotDuplicatedUniqueFields(){
        val categoryOriginal = categoryStub.copy(id = ULID.random())
        val categoryNewVersion = categoryStub.copy(id = ULID.random())

        every { dataSource.findByNameAndType(name = categoryOriginal.name, type = categoryOriginal.type) } returns categoryOriginal

        val error = assertThrows<InvalidUsecaseException> {
            usecase.execute(categoryNewVersion)
        }

        assertEquals("This actions can't be realized because conflict with previous data", error.message)
        verify(exactly = 0) { dataSource.saveAll(any()) }
    }

    @Test
    fun whenCategoryExistsAndHasSameIdShouldIgnoreDuplications(){
        val category = categoryStub.copy(id = ULID.random())

        every { dataSource.findByNameAndType(name = category.name, type = category.type) } returns category
        every { dataSource.save(category) } returns category

        usecase.execute(category)
    }
}