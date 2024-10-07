package com.example.kotlin.examenmovil.framework.views.fragments

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
}
