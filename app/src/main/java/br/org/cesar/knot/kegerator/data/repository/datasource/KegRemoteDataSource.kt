package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.remote.RestApi
import br.org.cesar.knot.kegerator.domain.model.Keg
import io.reactivex.Single
import java.util.*

class KegRemoteDataSource constructor(private val restApi: RestApi): KegDataSource {
    override fun list(): Single<List<Keg>> {
        return restApi.service.fetchKegs()
    }
}