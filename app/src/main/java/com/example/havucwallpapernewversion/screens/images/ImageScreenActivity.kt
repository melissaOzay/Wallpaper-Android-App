package com.example.havucwallpapernewversion.screens.images

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivityImageScreenBinding
import com.example.havucwallpapernewversion.screens.splash.SplashVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageScreenActivity: BaseActivity<ImageScreenVM, ActivityImageScreenBinding>() {

    lateinit var bottomNav : BottomNavigationView
    override val viewModel: ImageScreenVM by viewModels()
    override fun getViewBinding(): ActivityImageScreenBinding {
        return ActivityImageScreenBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        loadFragment(ImageScreenFragment())
        bottomNav = findViewById(R.id.bottomNav)
            bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                  R.id.home -> {
                      loadFragment(ImageScreenFragment())
                      true
                  }
                  R.id.message -> {
                      loadFragment(ImageScreenFragment())
                      true
                  }
                  R.id.settings -> {
                      loadFragment(ImageScreenFragment())
                      true
                  }
                else->
                    false
              }


          }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}