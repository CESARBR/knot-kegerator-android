package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.remote.RestApi
import br.org.cesar.knot.kegerator.domain.model.Keg
import java.util.*

class KegRemoteDataSource constructor(private val restApi: RestApi): KegDataSource {
    override fun getKegList(): List<Keg> {
        return restApi.service.fetchKegs().execute().body() ?: Collections.emptyList();
    }
}