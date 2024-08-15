package br.org.acal.apicore.application.rest.address.request

import br.org.acal.apicore.domain.entity.Address
import br.org.acal.apicore.domain.entity.Area
import io.azam.ulidj.ULID.random
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AddressCreateRequest (
    @field:NotNull(message = "Can't possible create an Address without an Area")
    @field:Valid
    val area: AreaRequest?,

    @field:NotBlank(message = "Can't possible create an Address without a Number")
    val number: String?,
    val letter: String?,
    val hasHydrometer: Boolean?,
) {

    fun toEntity(): Address {

        return Address(
            id = random(),
            number = number!!,
            letter = letter ?: "A",
            hasHydrometer = hasHydrometer ?: false,
            area = area!!.toArea(),
            active = true,
        )
    }
}

data class AreaRequest(
    @NotNull
    val id: String,
    @NotNull
    val name: String,
) {
    fun toArea(): Area = Area(id = id, name = name)
}

fun List<AddressCreateRequest>.toEntity(): List<Address> = map { it.toEntity() }
