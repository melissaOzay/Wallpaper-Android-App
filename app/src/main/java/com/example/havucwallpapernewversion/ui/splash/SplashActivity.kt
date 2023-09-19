package com.example.havucwallpapernewversion.ui.splash

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivitySplashBinding
import com.example.havucwallpapernewversion.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

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
            if (it) {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }
        }
    }
}
