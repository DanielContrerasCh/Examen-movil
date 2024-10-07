package com.example.kotlin.examenmovil.framework.views.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin.examenmovil.databinding.ActivityPlanetDetailBinding
import com.example.kotlin.examenmovil.framework.viewmodel.PlanetDetailViewModel
import com.example.kotlin.examenmovil.utils.Constants

class PlanetDetailActivity : AppCompatActivity() {
    // Inicializa el binding para acceder a las vistas en el layout
    private lateinit var binding: ActivityPlanetDetailBinding
    // Variable para almacenar la URL del planeta
    private var planetUrl: String? = null
    // Inicializa el ViewModel para gestionar la lógica de negocio
    private val viewModel: PlanetDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Maneja la intención recibida para obtener la URL del planeta
        manageIntent()
        // Inicializa el binding
        initializeBinding()
        // Inicializa los observadores para el LiveData
        initializeObservers()
        // Solicita los detalles del planeta utilizando la URL
        viewModel.getPlanetDetail(planetUrl!!.toInt())
    }

    // Función para inicializar el binding con el layout correspondiente
    private fun initializeBinding() {
        binding = ActivityPlanetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Función para inicializar los observadores del LiveData
    private fun initializeObservers() {
        // Observa los cambios en los datos del planeta
        viewModel.planetDetailLiveData.observe(this) { planet ->
            // Actualiza el nombre del planeta en la vista
            binding.tvPlanetName.text = planet.name
            // Actualiza el estado del planeta en la vista
            binding.tvPlanetStatus.text = if (planet.isDestroyed) "Status: Destruido" else "Status: Activo"
            // Muestra la descripción del planeta en la vista
            binding.tvPlanetDescription.text = "Description: ${planet.description}"

            // Carga la imagen del planeta utilizando Glide
            val urlImage = planet.image
            Glide.with(this).load(urlImage).into(binding.ivPlanet)

            // Extrae los nombres de los personajes y los establece en tvPlanetCharacters
            val characterNames = planet.characters.joinToString(", ") { it.name }
            binding.tvPlanetCharacters.text = "Characters: $characterNames"
        }
    }

    // Función para manejar la intención recibida
    private fun manageIntent() {
        // Verifica si la intención no es nula
        if (intent != null) {
            // Obtiene la URL del planeta desde la intención
            planetUrl = intent.getStringExtra(Constants.URL_PLANET)
            // Registra la URL en los logs para depuración
            Log.d("URL", planetUrl!!)
        }
    }
}
