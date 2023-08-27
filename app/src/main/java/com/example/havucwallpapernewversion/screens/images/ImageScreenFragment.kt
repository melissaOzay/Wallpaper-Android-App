package com.example.havucwallpapernewversion.screens.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
import com.example.havucwallpapernewversion.utility.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.adapter=recyclerViewAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 3)
        viewModel.imageList.observe(viewLifecycleOwner) { resource ->
                    recyclerViewAdapter.setListData(ArrayList(resource))
        }

        viewModel.getImage(0)
    }

    override fun initUI() {
        super.initUI()


    }
}