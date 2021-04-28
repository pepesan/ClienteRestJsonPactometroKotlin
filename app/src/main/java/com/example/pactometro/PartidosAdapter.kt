package com.example.pactometro

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pactometro.ServiceBuilder.URL
import com.squareup.picasso.Picasso

class PartidosAdapter(private val partyList: List<Partido>) :RecyclerView.Adapter<PartidosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.partido_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return partyList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${partyList.size} ")


        return holder.bind(partyList[position])

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var imageView = itemView.findViewById<ImageView>(R.id.ivFlag)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvCases = itemView.findViewById<TextView>(R.id.tvCases)
        fun bind(partido: Partido) {

            val name ="Partido :${partido.nombre.toString()}"
            tvTitle.text = partido.nombre
            tvCases.text = partido.dipu
            Picasso.get().load(URL+"img/"+partido.imagen).into(imageView)
        }

    }
}