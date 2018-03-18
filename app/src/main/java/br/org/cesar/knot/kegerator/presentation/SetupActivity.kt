package br.org.cesar.knot.kegerator.presentation

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.org.cesar.knot.kegerator.R
import br.org.cesar.knot.kegerator.injection.Injection
import br.org.cesar.knot.kegerator.presentation.extensions.getViewModel
import br.org.cesar.knot.kegerator.presentation.keg.KegsAdapter
import br.org.cesar.knot.kegerator.presentation.keg.KegsContract
import br.org.cesar.knot.kegerator.presentation.keg.KegsViewModel
import br.org.cesar.knot.kegerator.presentation.model.KegModel
import kotlinx.android.synthetic.main.activity_setup.*
import java.util.*

class SetupActivity : AppCompatActivity(), KegsContract.View {

    private val kegsViewModel by lazy { getViewModel<KegsViewModel>() }

    lateinit var kegsAdapter: KegsAdapter
    lateinit var kegsPresenter: KegsContract.Presenter

    override fun setPresenter(presenter: KegsContract.Presenter) {
        kegsPresenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        kegsPresenter = Injection.provideKegsPresenter(kegsViewModel)
        kegsAdapter = KegsAdapter()

        kegsViewModel.kegs.observe(this, KegsObserver())
        kegsViewModel.state.observe(this, StateObserver())

        setupRecycler()
    }

    override fun onStart() {
        super.onStart()
        kegsPresenter.start()
    }

    internal fun setupRecycler() {
        recycler_kegs.layoutManager = LinearLayoutManager(this)
        recycler_kegs.adapter = kegsAdapter
    }

    inner class KegsObserver: Observer<List<KegModel>> {
        override fun onChanged(t: List<KegModel>?) {
            kegsAdapter.kegs = t ?: Collections.emptyList();
            kegsAdapter.notifyDataSetChanged()
            recycler_kegs.visibility = View.VISIBLE
            progress.visibility = View.GONE
        }
    }

    inner class StateObserver: Observer<KegsViewModel.State> {
        override fun onChanged(state: KegsViewModel.State?) {
            when (state) {
            is KegsViewModel.State.Loading -> {
                recycler_kegs.visibility = View.GONE
                progress.visibility = View.VISIBLE
            }
            is KegsViewModel.State.Empty -> {
                recycler_kegs.visibility = View.GONE
                progress.visibility = View.GONE
            }
            is KegsViewModel.State.Error -> {
                recycler_kegs.visibility = View.GONE
                progress.visibility = View.GONE
            }
            }
        }
    }
}
