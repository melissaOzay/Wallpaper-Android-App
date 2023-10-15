package com.example.havucwallpapernewversion.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.havucwallpapernewversion.ui.progressBar.LoadingDialog

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

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = getViewBinding(inflater, container)
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initUI()

    }

}