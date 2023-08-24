package com.example.havucwallpapernewversion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.havucwallpapernewversion.screens.progressBar.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    private var loadingDialog: LoadingDialog? = null
    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): VB

    open fun setListeners() {}
    open fun initUI() {

    }

    abstract val viewModel: VM

    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (::binding.isInitialized) {
            return binding.root
        }
        binding = getViewBinding(inflater, container)
        viewModel.loadingState.observe(viewLifecycleOwner) {
            if (it.equals(true)) {
                if (loadingDialog == null) {
                    loadingDialog = LoadingDialog(requireContext())
                }
                loadingDialog?.showLoading()
            } else {
                loadingDialog?.hideLoading()
            }
            initUI()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initUI()

    }

    /*    fun navigate(direction: NavDirections) {
            loadingDialog?.showLoading()
            findNavController().navigate(direction)
            loadingDialog?.hideLoading()
        }*/
}