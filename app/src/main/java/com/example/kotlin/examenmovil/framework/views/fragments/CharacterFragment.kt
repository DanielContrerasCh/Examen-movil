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
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter: CharacterAdapter = CharacterAdapter()
    private lateinit var data: ArrayList<CharacterBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeComponents(root:View){
        recyclerView = root.findViewById(R.id.RVCharacters)
    }

    private fun initializeObservers() {
        viewModel.characterLiveData.observe(viewLifecycleOwner) { characterObject ->
            setUpRecyclerView(characterObject.items)
        }
    }

    private fun setUpRecyclerView(dataForList:ArrayList<CharacterBase>){
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = gridLayoutManager
        adapter.CharacterAdapter(dataForList,requireContext())
        recyclerView.adapter = adapter
    }

    private fun showRaceFilterDialog() {
        val races = arrayOf("Saiyan", "Namekian", "Human", "Android", "Frieza Race") // Lista de razas
        var selectedRace = ""
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona una raza")
        builder.setSingleChoiceItems(races, -1) { _, which ->
            selectedRace = races[which]
        }
        builder.setPositiveButton("Filtrar") { _, _ ->
            viewModel.filterByRaces(listOf(selectedRace)) // Filtra por la raza seleccionada
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

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
            selectedAffiliation = affiliations[which]
        }
        builder.setPositiveButton("Filtrar") { _, _ ->
            viewModel.filterByAffiliations(listOf(selectedAffiliation)) // Filtra por la afiliación seleccionada
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }
}
