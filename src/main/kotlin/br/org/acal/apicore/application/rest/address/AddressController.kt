package br.org.acal.apicore.application.rest.address

import br.org.acal.apicore.application.components.validator.ulid.ULIDValidator
import br.org.acal.apicore.application.rest.address.request.AddressCreateRequest
import br.org.acal.apicore.application.rest.address.request.AddressFilterRequest
import br.org.acal.apicore.application.rest.address.request.AreaPaginateRequestFilter
import br.org.acal.apicore.application.rest.address.request.toEntity
import br.org.acal.apicore.application.rest.address.response.AddressCreateResponse
import br.org.acal.apicore.application.rest.address.response.AddressFindAllResponse
import br.org.acal.apicore.application.rest.address.response.AddressFindByIdResponse
import br.org.acal.apicore.application.rest.address.response.AddressPaginateResponse
import br.org.acal.apicore.common.enums.Fixtures.Companion.ID
import br.org.acal.apicore.domain.usecases.address.AddressCreateLotUsecase
import br.org.acal.apicore.domain.usecases.address.AddressCreateUsecase
import br.org.acal.apicore.domain.usecases.address.AddressFindByIdUsecase
import br.org.acal.apicore.domain.usecases.address.AddressFindFilterUsecase
import br.org.acal.apicore.domain.usecases.address.AddressPaginateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("address", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class AddressController(
    private val create: AddressCreateUsecase,
    private val createLot: AddressCreateLotUsecase,
    private val findAll: AddressFindFilterUsecase,
    private val paginate: AddressPaginateUsecase,
    private val findById: AddressFindByIdUsecase,
): Sl4jLogger() {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: AddressCreateRequest): AddressCreateResponse =
        AddressCreateResponse(create.execute(request.toEntity()))

    @PostMapping("lot")
    fun lot(@Valid @RequestBody request: List<AddressCreateRequest>){
       createLot.execute(request.toEntity())
    }

     @GetMapping("paginate")
     fun paginateByFilter(
         @Valid request: AreaPaginateRequestFilter
     ): ResponseEntity<Page<AddressPaginateResponse>> = ok(
         paginate.execute(request.toAreaPaginateRequest())
             .map { AddressPaginateResponse(it) }.also {
             logger.info { "Returning customer /paginate $it" }
         }
     )

     @GetMapping("/find")
     fun findAllByFilter(
         addressFilterRequest: AddressFilterRequest
     ): ResponseEntity<List<AddressFindAllResponse>> {
         logger.info {
             "Querying customer Post/address find by $addressFilterRequest"
         }

         return ok(
             findAll.execute(addressFilterRequest.toAddressFilter()).map { AddressFindAllResponse(it) }
                 .also {
                     logger.info { "Returning customer /find ${it.size}" }
                 }
         )
     }

    @GetMapping(ID)
    fun get(@Valid @PathVariable @ULIDValidator id: String): ResponseEntity<AddressFindByIdResponse> = run {
        ok(AddressFindByIdResponse(findById.execute(id)))
    }

}


