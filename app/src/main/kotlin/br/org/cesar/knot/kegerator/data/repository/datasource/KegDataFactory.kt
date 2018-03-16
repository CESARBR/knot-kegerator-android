package br.org.cesar.knot.kegerator.data.repository

import br.org.cesar.knot.kegerator.data.remote.RestApi
import br.org.cesar.knot.kegerator.data.repository.datasource.KegDataSource
import br.org.cesar.knot.kegerator.data.repository.datasource.KegRemoteDataSource

class KegDataFactory constructor(private val restApi: RestApi) {
    fun getRemoteDataSource(): KegDataSource {
        return KegRemoteDataSource(restApi)
    }
}