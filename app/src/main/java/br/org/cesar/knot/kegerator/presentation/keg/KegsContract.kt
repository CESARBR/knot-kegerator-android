package br.org.cesar.knot.kegerator.presentation.keg

import br.org.cesar.knot.kegerator.presentation.BasePresenter
import br.org.cesar.knot.kegerator.presentation.BaseView
import br.org.cesar.knot.kegerator.presentation.model.KegModel

interface KegsContract {

    interface View: BaseView<Presenter>

    interface ViewModel {

        fun updateKegs(kegModels: List<KegModel>)

        fun changeToLoadingState()

        fun changeToErrorState(error: String)

        fun changeToEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveKegs()

    }
}