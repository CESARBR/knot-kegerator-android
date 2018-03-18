package br.org.cesar.knot.kegerator.presentation.keg

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.org.cesar.knot.kegerator.R
import br.org.cesar.knot.kegerator.presentation.model.KegModel

class KegsAdapter constructor(): RecyclerView.Adapter<KegsAdapter.ViewHolder>() {

    var kegs: List<KegModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keg = kegs[position]
        holder.nameText.text = keg.name
        holder.weightText.text = keg.weight
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_keg, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = kegs.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView
        var weightText: TextView

        init {
            nameText = view.findViewById(R.id.keg_name)
            weightText = view.findViewById(R.id.keg_weight)
        }
    }

}