package com.example.havucwallpapernewversion.screens.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.havucwallpapernewversion.MainActivity
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashVM,ActivitySplashBinding>() {

    override val viewModel: SplashVM by viewModels()
    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
    }
