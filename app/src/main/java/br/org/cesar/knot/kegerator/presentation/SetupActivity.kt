package br.org.cesar.knot.kegerator.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.org.cesar.knot.kegerator.R
import br.org.cesar.knot.kegerator.presentation.keg.KegsFragment
import br.org.cesar.knot.kegerator.presentation.model.KegModel

class SetupActivity : AppCompatActivity(), KegsFragment.OnKegSelectedListener {

    companion object {
        fun newIntent(context: Context) = Intent(context, SetupActivity::class.java)
    }

    private lateinit var mKegModel: KegModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.setup_root, KegsFragment.newInstance(), "kegsList")
                    .commit()
        }
    }

    override fun onKegSelected(kegModel: KegModel) {
        mKegModel = kegModel
        next()
    }

    fun next() {
        // TODO("not implemented, next fragment to be added here")
        /*
        val transaction = supportFragmentManager.beginTransaction()
        val secondFragment = FragmentTwo()
        transaction.replace(R.id.setup_root, secondFragment)
        transaction.addToBackStack(null)
        transaction.commit()
         */
    }
}
