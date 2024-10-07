package com.example.kotlin.examenmovil.framework.views.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examenmovil.R
import com.example.kotlin.examenmovil.data.network.model.CharacterBase
import com.example.kotlin.examenmovil.databinding.ActivityMainBinding
import com.example.kotlin.examenmovil.framework.adapters.CharacterAdapter
import com.example.kotlin.examenmovil.framework.viewmodel.CharacterViewModel
import com.example.kotlin.examenmovil.framework.viewmodel.MainViewModel
import com.example.kotlin.examenmovil.framework.views.fragments.CharacterFragment
import com.example.kotlin.examenmovil.framework.views.fragments.PlanetsFragment
import com.example.kotlin.examenmovil.utils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var currentFragment: Fragment
    private var currentMenuOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        initializeObservers()
        initializeListeners()
        exchangeCurrentFragment(CharacterFragment(), Constants.MENU_CHARACTER)

    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers(){

    }

    private fun initializeListeners(){
        binding.appBarMain.tvPersonajes.setOnClickListener{
            selectMenuOption(Constants.MENU_CHARACTER)
        }
        binding.appBarMain.tvPlanetas.setOnClickListener{
            selectMenuOption(Constants.MENU_PLANETS)
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption:String) {
        currentFragment = newFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()

        currentMenuOption = newMenuOption
    }

    private fun selectMenuOption(menuOption: String){
        if(menuOption == currentMenuOption){
            return
        }

        when(menuOption){
            Constants.MENU_CHARACTER -> exchangeCurrentFragment(CharacterFragment(),Constants.MENU_CHARACTER)
            Constants.MENU_PLANETS -> exchangeCurrentFragment(PlanetsFragment(),Constants.MENU_PLANETS)
        }
    }
}