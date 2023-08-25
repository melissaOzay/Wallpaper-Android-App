package com.example.havucwallpapernewversion.screens.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.domain.GetImagesUseCase
import com.example.havucwallpapernewversion.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ImageScreenVM @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,

    ) : BaseViewModel() {
    private val _imageList = MutableLiveData<Resource<List<ImageResponse>>>()
    val imageList: LiveData<Resource<List<ImageResponse>>> get() = _imageList

    init {
        viewModelScope.launch {
            getImage(0)
        }
    }

    private suspend fun getImage(page: Int) {
        try {
            _imageList.postValue(handleGetImage(getImagesUseCase.invoke(page)))
        } catch (t: Throwable) {

        }
    }

   private fun handleGetImage(response: Response<List<ImageResponse>>): Resource<List<ImageResponse>> {
        if (response.isSuccessful) {
            response.body().let {
                return Resource.Success(listOf())
            }
        }
        return Resource.Fail(response.message())
    }
}