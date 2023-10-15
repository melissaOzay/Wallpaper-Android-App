package com.example.havucwallpapernewversion.ui.home


import androidx.activity.viewModels
import com.example.havucwallpapernewversion.base.BaseActivity
import com.example.havucwallpapernewversion.databinding.ActivityImageScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeVM, ActivityImageScreenBinding>() {

    override val viewModel: HomeVM by viewModels()
    override fun getViewBinding(): ActivityImageScreenBinding {
        return ActivityImageScreenBinding.inflate(layoutInflater)
    }

}