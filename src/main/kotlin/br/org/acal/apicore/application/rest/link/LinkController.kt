package br.org.acal.apicore.application.rest.link

import br.org.acal.apicore.application.rest.link.request.LinkCreateLotRequest
import br.org.acal.apicore.application.rest.link.request.LinkCreateRequest
import br.org.acal.apicore.application.rest.link.request.LinkPaginateRequestFilter
import br.org.acal.apicore.application.rest.link.request.toEntity
import br.org.acal.apicore.application.rest.link.response.LinkCreateResponse
import br.org.acal.apicore.application.rest.link.response.LinkFindAllResponse
import br.org.acal.apicore.application.rest.link.response.LinkPaginateResponse
import br.org.acal.apicore.application.rest.link.response.toLinkPaginateResponse
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.usecases.link.LinkCreateLotUsecase
import br.org.acal.apicore.domain.usecases.link.LinkCreateUsecase
import br.org.acal.apicore.domain.usecases.link.LinkFindAllUsecase
import br.org.acal.apicore.domain.usecases.link.LinkPaginateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import br.org.acal.apicore.infrastructure.info
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("link", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class LinkController(
    private val create: LinkCreateUsecase,
    private val createLot: LinkCreateLotUsecase,
    private val findAll: LinkFindAllUsecase,
    private val paginate: LinkPaginateUsecase,
): Sl4jLogger() {

    @GetMapping("paginate")
    @ResponseStatus(OK)
    fun paginateByFilter(
        @Valid request: LinkPaginateRequestFilter
    ): Page<LinkPaginateResponse> =
        paginate.execute(request.toLinkPaginateRequest()).toLinkPaginateResponse()
            .also {
                logger.info { "Returning customer /paginate $it" }
            }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@Valid @RequestBody request: LinkCreateRequest): LinkCreateResponse =
        LinkCreateResponse(create.execute(request.toEntity()))

    @GetMapping
    @ResponseStatus(OK)
    fun findAll(): List<LinkFindAllResponse> {
        logger.info { "Querying link Get/" }
        return findAll.execute(Unit).map { LinkFindAllResponse(it) }.also {
            logger.info { "Find all link: size ${it.size}" }
        }
    }



    @PostMapping("/lot")
    fun lot(@Valid @RequestBody request: List<LinkCreateLotRequest>): ResponseEntity<Unit> = created(
        createLot.execute(request.toEntity())
    )


}


