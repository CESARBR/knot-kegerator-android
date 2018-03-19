package br.org.cesar.knot.kegerator.domain.repository

import br.org.cesar.knot.kegerator.domain.model.Beer
import io.reactivex.Single

/**
 * The beer repository provides endpoints for the caller to interact with beer data.
 */

interface BeerRepository {

    /**
     * Get the collection of beers.
     * @return a List of beers
     */
    fun list(): Single<List<Beer>>
}
