package br.org.cesar.knot.kegerator.data.repository.mapper

import br.org.cesar.knot.kegerator.data.repository.model.KegData
import br.org.cesar.knot.kegerator.domain.model.Keg
import java.util.*

open class KegMapper: Mapper<KegData, Keg> {
    override fun mapToData(type: Keg): KegData {
        return KegData(type.id.toString(), type.name, type.weight)
    }

    override fun mapFromData(type: KegData): Keg {
        return Keg(UUID.fromString(type.id), type.name, type.weight)
    }
}