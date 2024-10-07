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
import com.example.kotlin.examenmovil.data.network.model.PlanetBase
import com.example.kotlin.examenmovil.databinding.FragmentPlanetsBinding
import com.example.kotlin.examenmovil.framework.adapters.PlanetAdapter
import com.example.kotlin.examenmovil.framework.viewmodel.PlanetViewModel

class PlanetsFragment : Fragment() {
    // Variable de binding para acceder a las vistas del fragmento
    private var _binding: FragmentPlanetsBinding? = null
    private val binding get() = _binding!!

    // Inicializa el ViewModel para gestionar la lógica de negocio
    private lateinit var viewModel: PlanetViewModel

    // Inicializa el RecyclerView y su adaptador
    private lateinit var recyclerView: RecyclerView
    private val adapter: PlanetAdapter = PlanetAdapter()
    private lateinit var data: ArrayList<PlanetBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inicializa el ViewModel
        viewModel = ViewModelProvider(this)[PlanetViewModel::class.java]

        // Infla el layout del fragmento
        _binding = FragmentPlanetsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inicializa la lista de datos
        data = ArrayList()

        // Configura los componentes del fragmento
        initializeComponents(root)
        // Inicializa los observadores del LiveData
        initializeObservers()
        // Obtiene la lista de planetas desde el ViewModel
        viewModel.getPlanetList()

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
        recyclerView = root.findViewById(R.id.RVPlanets)
    }

    // Función para inicializar los observadores del LiveData
    private fun initializeObservers() {
        // Observa los cambios en los datos de planetas
        viewModel.planetLiveData.observe(viewLifecycleOwner) { planetObject ->
            // Configura el RecyclerView con la lista de planetas
            setUpRecyclerView(planetObject.items)
        }
    }

    // Función para configurar el RecyclerView
    private fun setUpRecyclerView(dataForList: ArrayList<PlanetBase>) {
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
        // Inicializa el adaptador con la lista de planetas
        adapter.PlanetAdapter(dataForList, requireContext())
        // Establece el adaptador en el RecyclerView
        recyclerView.adapter = adapter
    }
}
