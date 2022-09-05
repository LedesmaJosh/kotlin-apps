package com.example.perceptioncheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.perceptioncheck.databinding.ActivityMainBinding
import com.example.perceptioncheck.models.SpellViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel = SpellViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

    }
    override fun onSupportNavigateUp(): Boolean {
        viewModel.pages = 1
        return navController.navigateUp() || super.onSupportNavigateUp()

    }
}