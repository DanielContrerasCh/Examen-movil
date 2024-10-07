package com.example.kotlin.examenmovil.framework.views.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin.examenmovil.databinding.ActivityPlanetDetailBinding
import com.example.kotlin.examenmovil.framework.adapters.PlanetAdapter
import com.example.kotlin.examenmovil.framework.viewmodel.PlanetDetailViewModel
import com.example.kotlin.examenmovil.framework.viewmodel.PlanetViewModel
import com.example.kotlin.examenmovil.utils.Constants

class PlanetDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanetDetailBinding
    private var planetUrl:String? = null
    private val viewModel: PlanetDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        manageIntent()
        initializeBinding()
        initializeObservers()
        viewModel.getPlanetDetail(planetUrl!!.toInt())
    }

    private fun initializeBinding(){
        binding = ActivityPlanetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.planetDetailLiveData.observe(this) { planet ->
            binding.tvPlanetName.text = planet.name
            binding.tvPlanetStatus.text = if (planet.isDestroyed) "Status: Destruido" else "Status: Activo"
            binding.tvPlanetDescription.text = "Description: ${planet.description}"

            val urlImage = planet.image
            Glide.with(this).load(urlImage).into(binding.ivPlanet)

            // Extract character names and set to tvPlanetCharacters
            val characterNames = planet.characters.joinToString(", ") { it.name }
            binding.tvPlanetCharacters.text = "Characters: $characterNames"
        }
    }


    private fun manageIntent(){
        if(intent != null){
            planetUrl = intent.getStringExtra(Constants.URL_PLANET)
            Log.d("URL",planetUrl!!)
        }
    }
}