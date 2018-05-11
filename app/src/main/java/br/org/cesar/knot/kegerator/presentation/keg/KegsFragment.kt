package br.org.cesar.knot.kegerator.presentation.keg

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.org.cesar.knot.kegerator.R
import br.org.cesar.knot.kegerator.injection.Injection
import br.org.cesar.knot.kegerator.presentation.extensions.getViewModel
import br.org.cesar.knot.kegerator.presentation.model.KegModel
import kotlinx.android.synthetic.main.fragment_kegs.*
import java.util.*

class KegsFragment : Fragment(), KegsContract.View {

    companion object {
        fun newInstance() = KegsFragment()
    }

    private val mKegsViewModel by lazy { getViewModel<KegsViewModel>() }
    private var mListener: OnKegSelectedListener? = null
    private lateinit var mKegsAdapter: KegsAdapter
    private lateinit var mKegsPresenter: KegsContract.Presenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnKegSelectedListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString()
                    + " must implement OnKegSelectedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_kegs, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mKegsAdapter = KegsAdapter()

        mKegsAdapter.clickEvent.subscribe {
            mListener?.onKegSelected(it)
        }

        recycler_kegs.layoutManager = LinearLayoutManager(activity)
        recycler_kegs.adapter = mKegsAdapter

        setPresenter(Injection.provideKegsPresenter(mKegsViewModel))

        mKegsViewModel.kegs.observe(this, Observer {
            mKegsAdapter.kegs = it ?: Collections.emptyList()
            mKegsAdapter.notifyDataSetChanged()
        })

        mKegsViewModel.recyclerVisibility.observe(this, Observer {
            recycler_kegs.visibility = it ?: View.GONE
        })

        mKegsViewModel.progressVisibility.observe(this, Observer {
            progress.visibility = it ?: View.GONE
        })
    }

    override fun onStart() {
        super.onStart()
        mKegsPresenter.start()
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun setPresenter(presenter: KegsContract.Presenter) {
        mKegsPresenter = presenter
    }

    interface OnKegSelectedListener {
        fun onKegSelected(kegModel: KegModel)
    }

}
