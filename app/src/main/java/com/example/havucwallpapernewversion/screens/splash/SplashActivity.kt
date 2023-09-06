package com.example.havucwallpapernewversion.screens.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.havucwallpapernewversion.MainActivity
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivitySplashBinding
import com.example.havucwallpapernewversion.screens.images.ImageScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashVM, ActivitySplashBinding>() {

    override val viewModel: SplashVM by viewModels()
    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()
        observerErrorMessage()
        observerOpenMainView()

    }

    private fun observerErrorMessage() {
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this@SplashActivity, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun observerOpenMainView() {
        viewModel.openMainScreen.observe(this) {
            if (it == true) {
                startActivity(Intent(this@SplashActivity, ImageScreenActivity::class.java))

            }
        }
    }
}
