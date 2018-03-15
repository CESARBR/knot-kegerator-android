package br.org.cesar.knot.kegerator.domain.interactor

import br.org.cesar.knot.kegerator.domain.executor.PostExecutionThread
import br.org.cesar.knot.kegerator.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a UseCase.
 */
abstract class UseCase<Result> constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread
){

    private val disposables = CompositeDisposable()

    /**
     * Builds a [Single] which will be used when the current [UseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(): Single<Result>

    /**
     * Executes the current use case.
     */
    open fun execute(singleObserver: DisposableSingleObserver<Result>) {
        val single = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler) as Single<Result>
        addDisposable(single.subscribeWith(singleObserver))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}