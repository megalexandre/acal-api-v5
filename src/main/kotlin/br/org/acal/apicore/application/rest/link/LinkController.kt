package br.org.acal.apicore.application.rest.link

import br.org.acal.apicore.application.rest.link.request.LinkCreateLotRequest
import br.org.acal.apicore.application.rest.link.request.LinkCreateRequest
import br.org.acal.apicore.application.rest.link.request.LinkPaginateRequestFilter
import br.org.acal.apicore.application.rest.link.request.toEntity
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
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
    fun paginateByFilter(
        @Valid request: LinkPaginateRequestFilter
    ): ResponseEntity<Page<LinkPaginateResponse>> =
        ok(paginate.execute(request.toLinkPaginateRequest()).toLinkPaginateResponse()
            .also {
                logger.info { "Returning customer /paginate $it" }
            }
        )

    @GetMapping
    fun findAll(): ResponseEntity<List<Any>> {
        logger.info { "Querying link Get/" }
        return ok(
            findAll.execute(Unit).map { LinkFindAllResponse(it) }.also {
                logger.info { "Find all link: size ${it.size}" }
            }
        )
    }

    @PostMapping
    fun lot(@Valid @RequestBody request: LinkCreateRequest) {
        create.execute(request.toEntity())
    }

    @PostMapping("/lot")
    fun lot(@Valid @RequestBody request: List<LinkCreateLotRequest>): ResponseEntity<Unit> = created(
        createLot.execute(request.toEntity())
    )


}


