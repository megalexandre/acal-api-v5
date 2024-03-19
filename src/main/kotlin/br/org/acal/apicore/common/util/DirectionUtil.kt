package br.org.acal.apicore.common.util

import org.springframework.data.domain.Sort.Direction
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.Direction.DESC


class DirectionUtil{
    companion object{
        fun of(direction: String?): Direction = when(direction){
            "desc" -> DESC
            else -> ASC
        }

    }
}
