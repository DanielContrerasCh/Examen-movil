package com.example.kotlin.examenmovil.framework.viewholders

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.examenmovil.data.network.model.CharacterBase
import com.example.kotlin.examenmovil.databinding.ItemCharacterBinding
import com.example.kotlin.examenmovil.domain.CharacterListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterBase, context: Context) {
        binding.tvCharacterName.text = "Nombre: ${item.name}"
        binding.tvCharacterDescription.text = "Descripción: ${item.description}"
        binding.tvCharacterGender.text = "Genero: ${item.gender}"
        binding.tvCharacterRace.text = "Raza: ${item.race}"
        binding.tvCharacterAffiliation.text = "Afiliación: ${item.affiliation}"
        binding.tvCharacterKi.text = "Ki: ${item.ki}"
        binding.tvCharacterMaxKi.text = "Max Ki: ${item.maxKi}"
        getCharacterImage(item.image, binding.ivCharacter, context)
    }

    private fun getCharacterImage(url:String, imageView: ImageView, context: Context){
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