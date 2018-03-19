package br.org.cesar.knot.kegerator.domain.executor

import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * [br.org.cesar.knot.kegerator.domain.interactor.UseCase] out of the UI thread.
 */
interface ThreadExecutor : Executor
