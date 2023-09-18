package com.example.havucwallpapernewversion.screens.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentCategoryBinding
import com.example.havucwallpapernewversion.screens.categories.adapter.CategoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryVM>() {

    private val categoryAdapter by lazy {
        CategoryListAdapter(object : CategoryListAdapter.CategoryAdapterListener {
            override fun clickCategory(categoryTitle: String) {
                val action = CategoryFragmentDirections.actionCategoryFragmentToCategoryDetailFragment()
                action.title = categoryTitle
                view?.let { Navigation.findNavController(it).navigate(action) }
            }


        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentCategoryBinding {
        return FragmentCategoryBinding.inflate(layoutInflater)
    }

    override val viewModel: CategoryVM by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerView()
        observeImages()
    }

    private fun initRecylerView() {
        with(binding.recyclerView) {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun observeImages() {
        viewModel.categoryList.observe(viewLifecycleOwner) { resource ->
            categoryAdapter.setListData(ArrayList(resource))
        }
    }

}