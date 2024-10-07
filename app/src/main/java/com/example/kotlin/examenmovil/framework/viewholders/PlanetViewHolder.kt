package com.example.kotlin.examenmovil.framework.viewholders

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.examenmovil.data.network.model.PlanetBase
import com.example.kotlin.examenmovil.databinding.ItemPlanetBinding
import com.example.kotlin.examenmovil.framework.views.activities.PlanetDetailActivity
import com.example.kotlin.examenmovil.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetViewHolder(private val binding: ItemPlanetBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PlanetBase, context: Context) {
        binding.tvPlanetName.text = "Nombre: ${item.name}"
        binding.tvPlanetDescription.text = "Descripción: ${item.description}"
        binding.tvPlanetStatus.text = if (item.isDestroyed) "Status: Destruido" else "Status: Activo"

        binding.CVPlanet.setOnClickListener {
            passViewGoToPlanetDetail(item.id,context)
        }

        getPlanetImage(item.image, binding.ivPlanet, context)
    }

    private fun passViewGoToPlanetDetail(id: Int, context: Context) {
        var intent: Intent = Intent(context, PlanetDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(Constants.URL_PLANET,id.toString())
        context.startActivity(intent)
    }

    private fun getPlanetImage(url:String, imageView: ImageView, context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            CoroutineScope(Dispatchers.Main).launch {
                val requestOptions =  RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .priority(Priority.HIGH)

                Glide.with(context).load(url)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }
}