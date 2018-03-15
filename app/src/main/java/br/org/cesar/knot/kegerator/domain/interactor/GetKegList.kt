package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.executor.PostExecutionThread
import br.org.cesar.knot.kegerator.domain.executor.ThreadExecutor
import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.domain.repository.KegRepository
import io.reactivex.Single

/**
 * Use case used for retrieving a [List] of [Keg] instances from the [KegRepository]
 */
class GetKegList constructor(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        private val kegRepository: KegRepository
): UseCase<List<Keg>>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(): Single<List<Keg>> {
        return this.kegRepository.list()
    }
}