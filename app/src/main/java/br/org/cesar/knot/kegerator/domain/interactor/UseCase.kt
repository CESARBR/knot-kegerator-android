package br.org.cesar.knot.kegerator.domain.interactor

import io.reactivex.Single

/**
 * Abstract class for a UseCase.
 */
abstract class UseCase<Result, in Params> {
    abstract fun execute(params: Params? = null): Single<Result>
}
