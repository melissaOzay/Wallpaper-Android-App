package com.example.havucwallpapernewversion.ui.images

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
import com.example.havucwallpapernewversion.ui.images.adapter.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImagesFragment : BaseFragment<FragmentImageScreenBinding, ImagesVM>() {

    private val imagesAdapter by lazy {
        ImagesAdapter(listener = object : ImagesAdapter.ImagesAdapterListener {
            override fun likeOrUnLike(image: Image) {
                viewModel.likeOrUnLike(image)
            }
        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentImageScreenBinding {
        return FragmentImageScreenBinding.inflate(layoutInflater)
    }


    override val viewModel: ImagesVM by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeImages()
        initRecyclerView()
        observeErrorMessage()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getImage()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = imagesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.getImage()
                    }
                }
            })
        }
    }

    private fun observeImages() {
        viewModel.imageList.observe(viewLifecycleOwner) { resource ->
            imagesAdapter.setListData(ArrayList(resource))
        }
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { resource ->
            Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()
        }
    }


}