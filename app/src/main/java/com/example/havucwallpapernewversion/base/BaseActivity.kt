package com.example.havucwallpapernewversion.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.havucwallpapernewversion.ui.progressBar.LoadingDialog

abstract class BaseActivity<VM : BaseViewModel, B : ViewBinding> :
AppCompatActivity() {

    abstract val viewModel: VM

    private var loadingDialog: LoadingDialog? = null


    private var _binding: B? = null
    val binding get() = _binding!!

    open fun initialize() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadingState.observe(this) {
            if (it.equals(true)) {
                if (loadingDialog == null) {
                    loadingDialog = LoadingDialog(this)
                }
                loadingDialog?.showLoading()
            } else {
                loadingDialog?.hideLoading()
            }
        }
        _binding = getViewBinding()
        setContentView(binding.root)
        initialize()
    }

    abstract fun getViewBinding(): B

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


