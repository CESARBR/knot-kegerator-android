package br.org.cesar.knot.kegerator.data

import br.org.cesar.knot.kegerator.data.repository.KegDataSourceFactory
import br.org.cesar.knot.kegerator.data.repository.mapper.KegMapper
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import io.reactivex.Single

class KegGateway constructor(
        private val dataSourceFactory: KegDataSourceFactory,
        private val kegMapper: KegMapper
): KegRepository  {
    override fun list(): Single<List<Keg>> {
        return dataSourceFactory.createRemote().list()
                .map { list ->
                    list.map { kegMapper.mapFromEntity(it) }
                }
    }
}