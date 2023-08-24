package com.example.havucwallpapernewversion.screens.images

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.account.data.model.response.BaseResponse
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.domain.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageScreenVM @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,

    ) : BaseViewModel() {
    private val _imageList = MutableLiveData<Result<List<ImageResponse>>>()
    val imageList: LiveData<Result<List<ImageResponse>>> get() = _imageList

    init {
        getImage()
    }
    fun getImage(intervalType: Int = 0) {

        viewModelScope.launch {
            try {
                val images = getImagesUseCase.invoke(intervalType)
                _imageList.postValue(images)
            } catch (e: Exception) {
                // Handle the error here if needed
                // For example, you can post an error state to a different LiveData
            }
        }
    }
}