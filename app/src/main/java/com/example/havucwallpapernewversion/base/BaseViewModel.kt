package com.example.havucwallpapernewversion.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<String>()
    val messageState = MutableLiveData<String>()

    fun showLoading(){
        loadingState.value = true
    }

    fun hideLoading(){
        loadingState.value = false

    }

    fun showMessage(message:String){
        messageState.value = message
    }
    fun showError(errorMessage:String){
        errorState.value = errorMessage
    }
}