package br.org.cesar.knot.kegerator.data.remote.mapper

import br.org.cesar.knot.kegerator.data.remote.model.KegRemote
import br.org.cesar.knot.kegerator.data.repository.model.KegData

open class KegDataMapper: Mapper<KegRemote, KegData> {
    override fun mapToRemote(type: KegData)
            = KegRemote(type.id, type.name, type.weight)

    override fun mapFromRemote(type: KegRemote)
            = KegData(type.id, type.name, type.weight)
}