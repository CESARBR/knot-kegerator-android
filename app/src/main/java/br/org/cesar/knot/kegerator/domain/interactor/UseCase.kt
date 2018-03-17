package br.org.cesar.knot.kegerator.domain.interactor

import io.reactivex.Single

/**
 * Abstract class for a UseCase.
 */
open abstract class UseCase<Result, in Params> {
    open abstract fun execute(params: Params? = null): Single<Result>
}