package br.org.cesar.knot.kegerator.data.remote.mapper

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity

open class KegEntityMapper: Mapper<KegRemote, KegEntity> {
    override fun mapToRemote(type: KegEntity)
            = KegRemote(type.id, type.name, type.weight)

    override fun mapFromRemote(type: KegRemote)
            = KegEntity(type.id, type.name, type.weight)
}