package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.model.Beer
import br.org.cesar.knot.kegerator.domain.repository.BeerRepository
import io.reactivex.Single

/**
 * Use case used for retrieving a [List] of [Beer] instances from the [BeerRepository]
 */
class GetBeers constructor( private val beerRepository: BeerRepository
): UseCase<List<Beer>, Void?>() {

    override fun execute(params: Void?): Single<List<Beer>> {
        return this.beerRepository.list()
    }
}
