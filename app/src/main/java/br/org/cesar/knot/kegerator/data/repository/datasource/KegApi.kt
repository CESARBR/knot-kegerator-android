package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.data.repository.model.KegEntity
import io.reactivex.Single

interface KegApi {
    fun fetchKegs(): Single<List<KegEntity>>
}