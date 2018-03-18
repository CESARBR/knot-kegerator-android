package br.org.cesar.knot.kegerator.presentation.keg

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import br.org.cesar.knot.kegerator.presentation.model.KegModel

class KegsViewModel constructor(application: Application)
    : AndroidViewModel(application), KegsContract.ViewModel {

    var kegs = MutableLiveData<List<KegModel>>()
    var state = MutableLiveData<State>()
    var recyclerVisibility = MutableLiveData<Int>()
    var progressVisibility = MutableLiveData<Int>()

    override fun updateKegs(kegModels: List<KegModel>) {
        kegs.value = kegModels
        recyclerVisibility.value = View.VISIBLE
        progressVisibility.value = View.GONE
    }

    override fun changeToLoadingState() {
        state.value = State.Loading()
        recyclerVisibility.value = View.GONE
        progressVisibility.value = View.VISIBLE
    }

    override fun changeToErrorState(error: String) {
        state.value = State.Error(error)
        recyclerVisibility.value = View.GONE
        progressVisibility.value = View.GONE
    }

    override fun changeToEmptyState() {
        state.value = State.Empty()
        recyclerVisibility.value = View.GONE
        progressVisibility.value = View.GONE
    }

    sealed class State {
        class Loading : State()
        class Empty : State()
        class Error(val error: String) : State()
    }
}