package com.example.havucwallpapernewversion.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentCategoryBinding
import com.example.havucwallpapernewversion.ui.categories.adapter.CategoryListAdapter
import com.example.havucwallpapernewversion.ui.categories.adapter.`interface`.CategoryAdapterListener
import com.example.havucwallpapernewversion.ui.tab.TabFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryVM>() {

    override val viewModel: CategoryVM by viewModels()

    private val categoryAdapter by lazy {
        CategoryListAdapter(object : CategoryAdapterListener {
            override fun clickCategory(categoryTitle: String) {
                navigateCategoryDetail(categoryTitle)
            }
        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeImages()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun observeImages() {
        viewModel.categoryList.observe(viewLifecycleOwner) { resource ->
            categoryAdapter.setListData(resource)
        }
    }

    private fun navigateCategoryDetail(categoryTitle: String) {
        val navHostFragment = parentFragment as NavHostFragment?
        val parent: TabFragment? = navHostFragment!!.parentFragment as TabFragment?
        parent?.openCategoryDetail(categoryTitle)

    }

}