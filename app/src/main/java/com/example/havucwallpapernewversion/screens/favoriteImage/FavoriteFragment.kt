package com.example.havucwallpapernewversion.screens.favoriteImage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentFavoriteBinding
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.screens.images.adapter.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteVM>() {
    private val imageAdapter by lazy {
        ImagesAdapter(object : ImagesAdapter.ImagesAdapterListener {
            override fun addFavorite(image: Image) {
                viewModel.addFavorite(image)
            }

        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater)
    }


    override val viewModel: FavoriteVM by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerView()
        observeImages()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteImage()
        Log.e("Melisa", "on resume çalıştı")
    }

    private fun initRecylerView() {
        with(binding.recyclerView) {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

    }

    private fun observeImages() {
        viewModel.imageList.observe(viewLifecycleOwner) { resource ->
            imageAdapter.setListData(ArrayList(resource))
        }
    }


}