package br.org.cesar.knot.kegerator.data.remote

import br.org.cesar.knot.kegerator.data.remote.mapper.KegDataMapper
import br.org.cesar.knot.kegerator.data.repository.datasource.KegApi
import br.org.cesar.knot.kegerator.data.repository.model.KegData
import io.reactivex.Single

class RestApi constructor(
        private val service: KegeratorService,
        private val kegDataMapper: KegDataMapper
): KegApi {

    override fun fetchKegs(): Single<List<KegData>> {
        return service.fetchKegs().map { list ->
            list.map { kegDataMapper.mapFromRemote(it) }
        }
    }
}