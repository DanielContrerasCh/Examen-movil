package com.example.kotlin.examenmovil.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examenmovil.data.network.model.PlanetBase
import com.example.kotlin.examenmovil.databinding.ItemPlanetBinding
import com.example.kotlin.examenmovil.framework.viewholders.PlanetViewHolder

class PlanetAdapter : RecyclerView.Adapter<PlanetViewHolder>() {
    var data: ArrayList<PlanetBase> = ArrayList()
    private lateinit var context: Context

    fun PlanetAdapter(basicData: ArrayList<PlanetBase>, context: Context){
        this.data = basicData
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val binding = ItemPlanetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlanetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}