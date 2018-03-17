package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import io.reactivex.Single

open class KegRemoteDataSource constructor(private val kegApi: KegApi): KegDataSource {
    override fun list(): Single<List<KegEntity>> {
        return kegApi.fetchKegs()
    }
}