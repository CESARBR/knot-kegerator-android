package br.org.cesar.knot.kegerator.executor

import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import br.org.cesar.knot.kegerator.domain.interactor.UseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Wrapper class to execute uses cases considering subscribe/observe threads
 */
open class ObservableUseCase<Result, in Params> constructor(
        private val useCase: UseCase<Result, Params>,
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread
) {

    internal val disposables = CompositeDisposable()

    fun execute(singleObserver: DisposableSingleObserver<Result>, params: Params? = null) {
        val single = useCase.execute(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler) as Single<Result>
        addDisposable(single.subscribeWith(singleObserver))
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) = disposables.add(disposable)

}