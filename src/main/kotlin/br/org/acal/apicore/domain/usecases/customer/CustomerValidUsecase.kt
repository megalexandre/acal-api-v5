package br.org.acal.apicore.domain.usecases.customer

import br.org.acal.apicore.domain.Usecase
import br.org.acal.apicore.domain.dto.customer.CustomerValidationDocument
import br.org.acal.apicore.domain.entity.Customer
import org.springframework.stereotype.Service


@Service
class CustomerValidUsecase(
) : Usecase<List<Customer>, CustomerValidationDocument> {

    override fun execute(input: List<Customer>): CustomerValidationDocument =
        CustomerValidationDocument(
            valid = input.filter { it.documentNumber.isValid }.size ,
            invalid = input.filter { it.documentNumber.isInvalid }.size,
            validCustomer = input.filter { it.documentNumber.isValid },
            inValidCustomer = input.filter { it.documentNumber.isInvalid },
        )

}
