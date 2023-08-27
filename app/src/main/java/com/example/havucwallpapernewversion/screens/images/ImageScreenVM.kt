package com.example.havucwallpapernewversion.screens.images

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.domain.GetImagesUseCase
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageScreenVM @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
) : BaseViewModel() {
    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    fun getImage(page: Int) {
        viewModelScope.launch {
            try {
                val response = getImagesUseCase.invoke(page)
                var imageMap = response.body()!!.map {
                    it.toImage()
                }
                _imageList.postValue(imageMap)
            } catch (t: Throwable) {
            }
        }
    }


}