package com.example.havucwallpapernewversion.ui.images

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentImageScreenBinding
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.ui.imageDetail.ImageDetailActivity
import com.example.havucwallpapernewversion.ui.images.adapter.ImagesAdapter
import com.example.havucwallpapernewversion.ui.images.adapter.`interface`.ImagesAdapterListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImagesFragment : BaseFragment<FragmentImageScreenBinding, ImagesVM>() {

    override val viewModel: ImagesVM by viewModels()

    private val imagesAdapter by lazy {
        ImagesAdapter(listener = object : ImagesAdapterListener {
            override fun likeOrUnLike(image: Image) {
                viewModel.likeOrUnLike(image)
            }

            override fun clickItem(url: String, likeOrUnlike: Boolean, id: String) {
                val intent = Intent(activity, ImageDetailActivity::class.java)
                intent.apply {
                    putExtra("imageUrl", url)
                    putExtra("likeOrUnlike", likeOrUnlike)
                    putExtra("id", id)
                }
                startActivity(intent)
            }
        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentImageScreenBinding {
        return FragmentImageScreenBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeImages()
        initRecyclerView()
        observeErrorMessage()

    }

    override fun onStart() {
        super.onStart()
        categoryOrImage()

    }
    override fun onDestroyView() {
        binding.recyclerView.adapter=null
        super.onDestroyView()
    }
    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = imagesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)

        }
    }

    private fun categoryOrImage() {
        val args = arguments
        when (args?.getSerializable("type") as ImagesScreenType) {
            ImagesScreenType.HOME -> {
                viewModel.getImage()
            }

            ImagesScreenType.CATEGORY_DETAIL -> {
                val title = args.get("title")
                viewModel.getDetailCategory(title.toString())
            }

            ImagesScreenType.FAVORITE -> {
                viewModel.getFavoriteImage()
            }
        }
    }

    private fun observeImages() {
        viewModel.imageList.observe(viewLifecycleOwner) { resource ->
            imagesAdapter.setListData(resource)
            if (resource.isEmpty()) {
                with(binding){
                    ivPhoto.visibility = View.VISIBLE
                    tvTitle.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { resource ->
            Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()
        }
    }


}