package com.example.havucwallpapernewversion.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.account.data.model.request.RegisterUserRequest
import com.example.havucwallpapernewversion.features.account.domain.usecase.RegisterUserUseCase
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetImagesCategoryUseCase
import com.example.havucwallpapernewversion.helper.DeviceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val deviceHelper: DeviceHelper,
    private val getCategoryUseCase: GetImagesCategoryUseCase,
) : BaseViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    val openMainScreen = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
/*            val response = registerUserUseCase(
                RegisterUserRequest(
                    "fIU9lZa4RuWfJM4Bera4HK:APA91bEZ1P9g-TM556jLrFBjqapCKIOp3oaXTmwzKx1ke2Ly7fBnb_Nh9wFYCMqPp-RoxWEeEzuRRkImWRWezAzawoO2GPsptAECCSJoIUSjgB4Xn9h4ClDABIjRyxYkYbK61q0twzfN",
                    deviceHelper.getDeviceUniqueId()
                )
            )*/
            getCategoryUseCase()
            openMainScreen.value = true


/*            if (response.isFailure) {
                _errorMessage.postValue(response.getOrNull()?.error.orEmpty())
                openMainScreen.value = false
            } else {
                openMainScreen.value = true
            }*/
        }
    }
}