package com.example.kotlin.examenmovil.framework.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.examenmovil.R
import com.example.kotlin.examenmovil.databinding.ActivityMainBinding
import com.example.kotlin.examenmovil.framework.viewmodel.MainViewModel
import com.example.kotlin.examenmovil.framework.views.fragments.CharacterFragment
import com.example.kotlin.examenmovil.framework.views.fragments.PlanetsFragment
import com.example.kotlin.examenmovil.utils.Constants

class MainActivity : AppCompatActivity() {

    // Variable de binding para acceder a las vistas de la actividad
    private lateinit var binding: ActivityMainBinding

    // Inicializa el ViewModel para gestionar la lógica de negocio
    private val viewModel: MainViewModel by viewModels()

    // Variable para el fragmento actualmente visible
    private lateinit var currentFragment: Fragment
    // Variable para la opción de menú actualmente seleccionada
    private var currentMenuOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding, observadores y listeners
        initializeBinding()
        initializeObservers()
        initializeListeners()

        // Carga el fragmento de personajes como predeterminado
        exchangeCurrentFragment(CharacterFragment(), Constants.MENU_CHARACTER)
    }

    // Función para inicializar el binding
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Función para inicializar los observadores del LiveData (actualmente vacío)
    private fun initializeObservers() {
        // Aquí se pueden agregar observadores para el ViewModel si es necesario
    }

    // Función para inicializar los listeners de los elementos de la interfaz
    private fun initializeListeners() {
        // Listener para el botón de personajes en la barra de aplicaciones
        binding.appBarMain.tvPersonajes.setOnClickListener {
            selectMenuOption(Constants.MENU_CHARACTER) // Selecciona la opción de personajes
        }
        // Listener para el botón de planetas en la barra de aplicaciones
        binding.appBarMain.tvPlanetas.setOnClickListener {
            selectMenuOption(Constants.MENU_PLANETS) // Selecciona la opción de planetas
        }
    }

    // Función para intercambiar el fragmento actual por uno nuevo
    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment // Actualiza el fragmento actual

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment) // Reemplaza el fragmento en el contenedor
            .commit()

        currentMenuOption = newMenuOption // Actualiza la opción de menú actual
    }

    // Función para seleccionar una opción de menú
    private fun selectMenuOption(menuOption: String) {
        // Si la opción seleccionada es la misma que la actual, no hace nada
        if (menuOption == currentMenuOption) {
            return
        }

        // Cambia el fragmento según la opción de menú seleccionada
        when (menuOption) {
            Constants.MENU_CHARACTER -> exchangeCurrentFragment(CharacterFragment(), Constants.MENU_CHARACTER)
            Constants.MENU_PLANETS -> exchangeCurrentFragment(PlanetsFragment(), Constants.MENU_PLANETS)
        }
    }
}
