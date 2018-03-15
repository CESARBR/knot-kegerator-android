package br.org.cesar.knot.kegerator.domain.interactor

abstract class UseCase<T, in Params> constructor(){

    /**
     * Executes the current use case
     */
    protected abstract fun execute(params: Params? = null): T
}