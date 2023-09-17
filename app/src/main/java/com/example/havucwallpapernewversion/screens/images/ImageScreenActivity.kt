package com.example.havucwallpapernewversion.screens.images

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivityImageScreenBinding
import com.example.havucwallpapernewversion.screens.categories.CategoryFragment
import com.example.havucwallpapernewversion.screens.favoriteImage.FavoriteFragment
import com.example.havucwallpapernewversion.screens.splash.SplashVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageScreenActivity : BaseActivity<ImageScreenVM, ActivityImageScreenBinding>() {

    override val viewModel: ImageScreenVM by viewModels()
    override fun getViewBinding(): ActivityImageScreenBinding {
        return ActivityImageScreenBinding.inflate(layoutInflater)
    }

    private val imageScreenFragment by lazy {
        ImageScreenFragment()
    }
    private val favoriteFragment by lazy {
        FavoriteFragment()
    }
    private val categoryFragment by lazy {
        CategoryFragment()
    }
    override fun initialize() {
        super.initialize()
        loadFragment(imageScreenFragment)
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(imageScreenFragment)
                    true
                }

                R.id.favorite -> {
                    loadFragment(favoriteFragment)
                    true
                }

                R.id.category -> {
                    loadFragment(categoryFragment)
                    true
                }

                else ->
                    false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }



}