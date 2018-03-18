package br.org.cesar.knot.kegerator.presentation.keg

import br.org.cesar.knot.kegerator.domain.model.Keg
import br.org.cesar.knot.kegerator.executor.ObservableUseCase
import br.org.cesar.knot.kegerator.presentation.mapper.KegModelMapper
import io.reactivex.observers.DisposableSingleObserver

class KegsPresenter constructor(
        private val viewModel: KegsContract.ViewModel,
        private val getKegsUseCase: ObservableUseCase<List<Keg>, Void?>,
        private val kegModelMapper: KegModelMapper
): KegsContract.Presenter {

    override fun start() {
        retrieveKegs()
    }

    override fun stop() {
        getKegsUseCase.dispose()
    }

    override fun retrieveKegs() {
        viewModel.changeToLoadingState()
        getKegsUseCase.execute(KegSubscriber())
    }

    internal fun handleGetKegsSuccess(kegs: List<Keg>) {
        if (kegs.isEmpty()) {
            viewModel.changeToEmptyState()
        } else {
            viewModel.updateKegs(kegs.map { kegModelMapper.mapToModel(it) })
        }
    }

    inner class KegSubscriber: DisposableSingleObserver<List<Keg>>() {

        override fun onSuccess(t: List<Keg>) {
            handleGetKegsSuccess(t)
        }

        override fun onError(exception: Throwable) {
            // TODO: Pass a string identifying the error
            viewModel.changeToErrorState("")
        }
    }
}