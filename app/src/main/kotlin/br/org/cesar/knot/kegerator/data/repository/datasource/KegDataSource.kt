package br.org.cesar.knot.kegerator.data.repository.datasource

import br.org.cesar.knot.kegerator.domain.model.Keg

interface KegDataSource {
    fun getKegList(): List<Keg>
}