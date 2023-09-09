package com.example.havucwallpapernewversion.screens.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentImageScreenBinding
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.screens.images.adapter.FavoriteAdapter
import com.example.havucwallpapernewversion.screens.images.adapter.ImageScreenAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageScreenFragment : BaseFragment<FragmentImageScreenBinding, ImageScreenVM>() {
    private val recyclerViewAdapter by lazy {
       ImageScreenAdapter(listener =object : ImageScreenAdapter.HomeAdapterListener {
           override fun addFavorite(entity: Image) {
               viewModel.addFavorite(entity)
           }

       })
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
        initRecylerView()
        observeImages()
        //observeErrorMessage()
        viewModel.getImage()

    }

    private fun initRecylerView() {
        recyclerView = binding.recyclerView
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 3)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getImage()
                }
            }
        })
    }

    private fun observeImages() {
        viewModel.imageList.observe(viewLifecycleOwner) { resource ->
            recyclerViewAdapter.setListData(ArrayList(resource))
        }
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { resource ->
            Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()
        }
    }


}