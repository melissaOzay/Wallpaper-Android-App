package com.example.havucwallpapernewversion.screens.images

import android.os.Bundle
import android.util.Log
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
class ImageScreenFragment : BaseFragment<FragmentImageScreenBinding, ImageScreenVM>() {
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
    var isLastPage = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 3)
        viewModel.getImage()
        viewModel.imageList.observe(viewLifecycleOwner) { resource ->
            recyclerViewAdapter.setListData(ArrayList(resource))

        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        isLastPage = true
                        viewModel.getImage()
                        isLastPage = false
                    }
                }
            }
        })
    }


}