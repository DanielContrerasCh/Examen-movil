package com.example.kotlin.examenmovil.framework.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examenmovil.R
import com.example.kotlin.examenmovil.data.network.model.CharacterBase
import com.example.kotlin.examenmovil.databinding.FragmentCharactersBinding
import com.example.kotlin.examenmovil.framework.adapters.CharacterAdapter
import com.example.kotlin.examenmovil.framework.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {
    // Variable de binding para acceder a las vistas del fragmento
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    // Inicializa el ViewModel para gestionar la lógica de negocio
    private lateinit var viewModel: CharacterViewModel

    // Inicializa el RecyclerView y su adaptador
    private lateinit var recyclerView: RecyclerView
    private val adapter: CharacterAdapter = CharacterAdapter()
    private lateinit var data: ArrayList<CharacterBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        // Infla el layout del fragmento
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inicializa la lista de datos
        data = ArrayList()

        // Configura los componentes del fragmento
        initializeComponents(root)
        // Inicializa los observadores del LiveData
        initializeObservers()
        // Obtiene la lista de personajes desde el ViewModel
        viewModel.getCharacterList()

        // Abrir diálogo de selección de raza
        binding.btnFilterRace.setOnClickListener {
            showRaceFilterDialog()
        }

        // Abrir diálogo de selección de afiliación
        binding.btnFilterAffiliation.setOnClickListener {
            showAffiliationFilterDialog()
        }

        // Restablecer filtros
        binding.btnResetFilters.setOnClickListener {
            viewModel.resetFilters()
        }

        // Devuelve la vista raíz del fragmento
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpia el binding cuando se destruye la vista
        _binding = null
    }

    // Función para inicializar componentes visuales
    private fun initializeComponents(root: View) {
        // Encuentra el RecyclerView en el layout
        recyclerView = root.findViewById(R.id.RVCharacters)
    }

    // Función para inicializar los observadores del LiveData
    private fun initializeObservers() {
        // Observa los cambios en los datos de personajes
        viewModel.characterLiveData.observe(viewLifecycleOwner) { characterObject ->
            // Configura el RecyclerView con la lista de personajes
            setUpRecyclerView(characterObject.items)
        }
    }

    // Función para configurar el RecyclerView
    private fun setUpRecyclerView(dataForList: ArrayList<CharacterBase>) {
        // Establece que el tamaño del RecyclerView es fijo
        recyclerView.setHasFixedSize(true)
        // Crea un GridLayoutManager para el RecyclerView
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        // Establece el LayoutManager en el RecyclerView
        recyclerView.layoutManager = gridLayoutManager
        // Inicializa el adaptador con la lista de personajes
        adapter.CharacterAdapter(dataForList, requireContext())
        // Establece el adaptador en el RecyclerView
        recyclerView.adapter = adapter
    }

    // Función para mostrar el diálogo de filtro por raza
    private fun showRaceFilterDialog() {
        val races = arrayOf("Saiyan", "Namekian", "Human", "Android", "Frieza Race") // Lista de razas
        var selectedRace = ""
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona una raza")
        builder.setSingleChoiceItems(races, -1) { _, which ->
            selectedRace = races[which] // Guarda la raza seleccionada
        }
        builder.setPositiveButton("Filtrar") { _, _ ->
            viewModel.filterByRaces(listOf(selectedRace)) // Filtra por la raza seleccionada
        }
        builder.setNegativeButton("Cancelar", null) // Cierra el diálogo sin hacer nada
        builder.show() // Muestra el diálogo
    }

    // Función para mostrar el diálogo de filtro por afiliación
    private fun showAffiliationFilterDialog() {
        val affiliations = arrayOf("Z Fighter",
            "Army of Frieza",
            "Freelancer",
            "Villain",
            "Other",
            "Pride Troopers") // Lista de afiliaciones
        var selectedAffiliation = ""
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona una afiliación")
        builder.setSingleChoiceItems(affiliations, -1) { _, which ->
            selectedAffiliation = affiliations[which] // Guarda la afiliación seleccionada
        }
        builder.setPositiveButton("Filtrar") { _, _ ->
            viewModel.filterByAffiliations(listOf(selectedAffiliation)) // Filtra por la afiliación seleccionada
        }
        builder.setNegativeButton("Cancelar", null) // Cierra el diálogo sin hacer nada
        builder.show() // Muestra el diálogo
    }
}
