package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository

/**
 * Use case used for retreiving a [List] of [Keg] instances from the [KegRepository]
 */
class GetKegList constructor(val kegRepository: KegRepository): UseCase<List<Keg>, Void?>() {

    public override fun execute(params: Void?): List<Keg> {
        return this.kegRepository.getKegList()
    }
}