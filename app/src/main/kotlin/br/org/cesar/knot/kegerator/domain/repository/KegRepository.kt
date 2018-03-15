package br.org.cesar.knot.kegerator.domain.repository

import br.org.cesar.knot.kegerator.domain.model.Keg

interface KegRepository {

    fun getKegList(): List<Keg>
}