package com.example.havucwallpapernewversion.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.account.domain.usecase.RegisterUserUseCase
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetCategoriesUseCase
import com.example.havucwallpapernewversion.helper.DeviceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val deviceHelper: DeviceHelper,
    private val getCategoryUseCase: GetCategoriesUseCase,
) : BaseViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    val openMainScreen = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            openMainScreen.value = true
            delay(2000)

            getCategoryUseCase.invoke().collect {
                Log.d("", "")
            }

        }
    }
}