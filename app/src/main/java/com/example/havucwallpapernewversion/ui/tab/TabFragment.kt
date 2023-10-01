package com.example.havucwallpapernewversion.ui.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.base.BaseFragment
import com.example.havucwallpapernewversion.databinding.FragmentTabBinding
import com.example.havucwallpapernewversion.ui.images.ImagesScreenType
import com.google.android.material.bottomnavigation.BottomNavigationView

class TabFragment : BaseFragment<FragmentTabBinding, TabVM>() {

    override val viewModel: TabVM by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean
    ): FragmentTabBinding {
        return FragmentTabBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageBottomMenu()
    }

    private fun manageBottomMenu() {
        val bottomNavigationView = requireView().findViewById<BottomNavigationView>(R.id.bottomNav)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.tab_container)
        val navController = navHostFragment?.findNavController()
        if (navController != null) {
            bottomNavigationView.setupWithNavController(navController)
        }
    }

    fun openCategoryDetail(categoryTitle: String) {
        val action =
            TabFragmentDirections.actionTabFragmentToCategoryDetailFragment()

        action.apply {
            type = ImagesScreenType.CATEGORY_DETAIL
            title = categoryTitle
        }
        findNavController().navigate(action)
    }


}
