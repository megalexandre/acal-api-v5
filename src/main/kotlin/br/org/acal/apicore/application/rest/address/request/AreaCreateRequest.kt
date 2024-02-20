package br.org.acal.apicore.application.rest.address.request

import br.org.acal.apicore.application.rest.components.adapter.RequestAdapter
import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area
import io.azam.ulidj.ULID.random
import org.springframework.validation.annotation.Validated

@Validated
data class AddressCreateRequest (

    val id: String?,
    val area: Area?,

    val areaId: String?,
    val areaName: String?,

    val number: String,
    val letter: String?,
    val hasHydrometer: Boolean?,

): RequestAdapter<Address> {

    override fun toEntity(): Address = Address(
        id = id ?: random(),
        number = number,
        letter = letter ?: "A",
        hasHydrometer = hasHydrometer ?: true,
        area = area ?: Area(id = areaId ?: throw RuntimeException(), name = areaName ?: throw RuntimeException()),
    )
}

fun List<AddressCreateRequest>.toEntity(): List<Address> = map { it.toEntity() }