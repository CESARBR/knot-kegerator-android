package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.repository.model.KegData
import io.reactivex.Single

interface KegDataSource {
    fun list(): Single<List<KegData>>
}