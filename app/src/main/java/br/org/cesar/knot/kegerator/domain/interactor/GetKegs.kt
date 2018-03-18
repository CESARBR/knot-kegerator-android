package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import io.reactivex.Single

/**
 * Use case for retrieving [Keg] instances from the [KegRepository]
 */
open class GetKegs constructor(
        private val kegRepository: KegRepository
): UseCase<List<Keg>, Void?>() {

    override fun execute(params: Void?): Single<List<Keg>> {
        return this.kegRepository.list()
    }
}