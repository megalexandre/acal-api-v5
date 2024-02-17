package br.org.acal.apicore.application.rest.link

import br.org.acal.apicore.application.rest.link.request.LinkCreateRequest
import br.org.acal.apicore.application.rest.link.request.toEntity
import br.org.acal.apicore.common.util.ResponseEntityUtil.Companion.created
import br.org.acal.apicore.domain.usecases.link.LinkCreateUsecase
import br.org.acal.apicore.infrastructure.Sl4jLogger
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("link", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class LinkController(
    private val create: LinkCreateUsecase,
): Sl4jLogger() {

    @PostMapping
    fun create(@Valid @RequestBody request: List<LinkCreateRequest>): ResponseEntity<Unit> {
        return created(
            create.execute(request.toEntity())
        )
    }


}


