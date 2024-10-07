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
    private var _binding: FragmentPlanetsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PlanetViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter: PlanetAdapter = PlanetAdapter()
    private lateinit var data: ArrayList<PlanetBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PlanetViewModel::class.java]

        _binding = FragmentPlanetsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
        viewModel.getPlanetList()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeComponents(root: View){
        recyclerView = root.findViewById(R.id.RVPlanets)
    }

    private fun initializeObservers() {
        viewModel.planetLiveData.observe(viewLifecycleOwner) { planetObject ->
            setUpRecyclerView(planetObject.items)
        }
    }

    private fun setUpRecyclerView(dataForList:ArrayList<PlanetBase>){
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PlanetAdapter(dataForList,requireContext())
        recyclerView.adapter = adapter
    }
}