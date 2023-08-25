package com.example.havucwallpapernewversion.screens.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.ActivityImageScreenBinding
import com.example.havucwallpapernewversion.databinding.FragmentImageScreenBinding
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.screens.images.adapter.ImageScreenAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageScreenFragment :BaseFragment<FragmentImageScreenBinding,ImageScreenVM>() {
    private val recyclerViewAdapter by lazy {
        ImageScreenAdapter()
    }
    private lateinit var recyclerView: RecyclerView
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentImageScreenBinding {
        return FragmentImageScreenBinding.inflate(layoutInflater)
    }

    override val viewModel: ImageScreenVM by viewModels()
    override fun initUI() {
        super.initUI()
        recyclerView = binding.recyclerView
        recyclerView.adapter=recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.imageList.observe(requireActivity()){
            recyclerViewAdapter.setListData(it as ArrayList<ImageResponse>)
        }

    }
}