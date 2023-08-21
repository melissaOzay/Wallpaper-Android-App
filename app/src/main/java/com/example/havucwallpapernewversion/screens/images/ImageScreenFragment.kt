package com.example.havucwallpapernewversion.screens.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.ActivityImageScreenBinding
import com.example.havucwallpapernewversion.databinding.FragmentImageScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageScreenFragment :BaseFragment<FragmentImageScreenBinding,ImageScreenVM>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentImageScreenBinding {
        return FragmentImageScreenBinding.inflate(layoutInflater)
    }

    override val viewModel: ImageScreenVM
        get() = ImageScreenVM()

}