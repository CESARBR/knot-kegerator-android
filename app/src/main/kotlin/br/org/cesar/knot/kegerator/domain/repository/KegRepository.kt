package br.org.cesar.knot.kegerator.domain.repository

import br.org.cesar.knot.kegerator.domain.model.Keg

/**
 * The keg repository provides endpoints for the caller to interact with keg data.
 */
interface KegRepository {

    /**
     * Get the collection of kegs.
     * @return a List of kegs
     */
    fun getKegList(): List<Keg>
}