package br.org.acal.apicore.application.rest.link.response

import br.org.acal.apicore.domain.entity.Link

class LinkCreateResponse (
    val id: String
){
    constructor(link: Link) : this(
        id = link.id,
    )
}
