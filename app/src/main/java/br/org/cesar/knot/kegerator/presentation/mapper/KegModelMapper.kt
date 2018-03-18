package br.org.cesar.knot.kegerator.presentation.mapper

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.presentation.model.KegModel
import java.util.*

open class KegModelMapper: Mapper<KegModel, Keg> {
    override fun mapToModel(type: Keg): KegModel {
        return KegModel(type.id.toString(), type.name, type.weight.toString())
    }

    override fun mapFromModel(type: KegModel): Keg {
        return Keg(UUID.fromString(type.id), type.name, type.weight.toInt())
    }
}