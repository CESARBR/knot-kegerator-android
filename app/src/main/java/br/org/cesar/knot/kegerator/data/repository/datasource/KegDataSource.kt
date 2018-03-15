package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.domain.model.Keg
import io.reactivex.Single

interface KegDataSource {
    fun list(): Single<List<Keg>>
}