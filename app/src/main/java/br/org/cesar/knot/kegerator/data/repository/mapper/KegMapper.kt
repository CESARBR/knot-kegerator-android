package br.org.cesar.knot.kegerator.data.repository.mapper

import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import br.org.cesar.knot.kegerator.domain.model.Keg
import java.util.*

open class KegMapper: Mapper<KegEntity, Keg> {
    override fun mapToEntity(type: Keg): KegEntity {
        return KegEntity(type.id.toString(), type.name, type.weight)
    }

    override fun mapFromEntity(type: KegEntity): Keg {
        return Keg(UUID.fromString(type.id), type.name, type.weight)
    }
}