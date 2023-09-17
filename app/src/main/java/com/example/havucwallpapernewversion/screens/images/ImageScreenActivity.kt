package com.example.havucwallpapernewversion.screens.images

import android.R
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.ui.NavigationUI
import androidx.navigation.findNavController
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivityImageScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageScreenActivity : BaseActivity<ImageScreenVM, ActivityImageScreenBinding>() {

    override val viewModel: ImageScreenVM by viewModels()
    override fun getViewBinding(): ActivityImageScreenBinding {
        return ActivityImageScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.container)
        NavigationUI.setupWithNavController(navHostFragment, navController)
    }

}