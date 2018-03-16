package br.org.cesar.knot.kegerator.data

import br.org.cesar.knot.kegerator.data.repository.KegDataFactory
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository

class KegDataRepository constructor(private val dataFactory: KegDataFactory): KegRepository  {
    override fun getKegList(): List<Keg> {
        return dataFactory.getRemoteDataSource().getKegList()
    }
}