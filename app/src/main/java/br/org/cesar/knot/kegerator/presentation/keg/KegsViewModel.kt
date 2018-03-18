package br.org.cesar.knot.kegerator.presentation.keg

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.org.cesar.knot.kegerator.presentation.model.KegModel

class KegsViewModel constructor(application: Application)
    : AndroidViewModel(application), KegsContract.ViewModel {

    var kegs = MutableLiveData<List<KegModel>>()
    var state = MutableLiveData<State>()

    override fun updateKegs(kegModels: List<KegModel>) {
        kegs.value = kegModels
    }

    override fun changeToLoadingState() {
        state.value = State.Loading()
    }

    override fun changeToErrorState(error: String) {
        state.value = State.Error(error)
    }

    override fun changeToEmptyState() {
        state.value = State.Empty()
    }

    sealed class State {
        class Loading : State()
        class Empty : State()
        class Error(val error: String) : State()
    }
}