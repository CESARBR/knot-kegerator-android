package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.mapper.KegEntityMapper
import br.org.cesar.knot.kegerator.data.repository.datasource.KegApi
import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import io.reactivex.Single

class RestApi constructor(
        private val service: KegeratorService,
        private val kegEntityMapper: KegEntityMapper
): KegApi {

    override fun fetchKegs(): Single<List<KegEntity>> {
        return service.fetchKegs().map { list ->
            list.map { kegEntityMapper.mapFromRemote(it) }
        }
    }
}