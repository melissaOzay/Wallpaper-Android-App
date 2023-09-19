package com.example.havucwallpapernewversion.ui.categoryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentCategoryDetailBinding
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.ui.images.adapter.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailFragment : BaseFragment<FragmentCategoryDetailBinding, CategoryDetailVM>() {
    private val categoryDetailAdapter by lazy {
        ImagesAdapter(listener = object : ImagesAdapter.ImagesAdapterListener {
            override fun likeOrUnLike(image: Image) {
                viewModel.likeOrUnLike(image)
            }
        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCategoryDetailBinding {
        return FragmentCategoryDetailBinding.inflate(layoutInflater)
    }


    override val viewModel: CategoryDetailVM by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeImages()
        initRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        val categoryTitle = CategoryDetailFragmentArgs.fromBundle(requireArguments()).title
        viewModel.getDetailCategory(categoryTitle)
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = categoryDetailAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val categoryTitle =
                            CategoryDetailFragmentArgs.fromBundle(requireArguments()).title
                        viewModel.getDetailCategory(categoryTitle)

                    }
                }
            })
        }
    }

    private fun observeImages() {
        viewModel.categoryDetailList.observe(viewLifecycleOwner) { resource ->
            categoryDetailAdapter.setListData(ArrayList(resource))
        }
    }


}