package br.org.acal.apicore.application.rest.address

import br.org.acal.apicore.application.rest.address.request.AddressCreateRequest
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.usecases.address.AddressCreateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("address", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class AddressController(
    private val create: AddressCreateUsecase,
): Sl4jLogger() {

    @PostMapping
    fun create(@Valid @RequestBody request: AddressCreateRequest): ResponseEntity<Address> {
        return created(
            create.execute(request.toEntity())
        )
    }


}


