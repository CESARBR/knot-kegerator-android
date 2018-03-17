package br.org.cesar.knot.kegerator.data.repository

import br.org.cesar.knot.kegerator.data.remote.RestApi
import br.org.cesar.knot.kegerator.data.repository.datasource.KegApi
import br.org.cesar.knot.kegerator.data.repository.datasource.KegDataSource
import br.org.cesar.knot.kegerator.data.repository.datasource.KegRemoteDataSource

open class KegDataSourceFactory constructor(
        private val kegRemoteDataSource: KegRemoteDataSource) {
    open fun createRemote(): KegDataSource {
        return kegRemoteDataSource
    }
}