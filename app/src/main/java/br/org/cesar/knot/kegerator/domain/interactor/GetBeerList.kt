package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.executor.PostExecutionThread
import br.org.cesar.knot.kegerator.domain.executor.ThreadExecutor
import br.org.cesar.knot.kegerator.domain.model.Beer
import br.org.cesar.knot.kegerator.domain.repository.BeerRepository
import io.reactivex.Single

/**
 * Use case used for retrieving a [List] of [Beer] instances from the [BeerRepository]
 */
class GetBeerList constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val beerRepository: BeerRepository
): UseCase<List<Beer>>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(): Single<List<Beer>> {
        return this.beerRepository.list()
    }
}
