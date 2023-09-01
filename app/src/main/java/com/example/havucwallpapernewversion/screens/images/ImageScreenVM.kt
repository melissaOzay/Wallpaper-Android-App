package com.example.havucwallpapernewversion.screens.images

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.domain.GetImagesUseCase
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageScreenVM @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
) : BaseViewModel() {
    private val _imageList = MutableLiveData<List<Image>>()
    private val currentPage = MutableLiveData<Int>()
    val imageList: LiveData<List<Image>> get() = _imageList
    init {
        currentPage.value = 0

    }
    fun getImage() {
        val nextPage = currentPage.value ?: 0
        viewModelScope.launch {
            try {
                val response = getImagesUseCase.invoke(nextPage)
                if (response.isSuccessful) {
                    var imageMap: List<Image> = response.body()!!.map {
                        it.toImage()
                    } ?: emptyList()
                    val currentList = _imageList.value?.toMutableList() ?: mutableListOf()
                    currentList.addAll(imageMap)
                    _imageList.postValue(currentList)
                    currentPage.postValue(nextPage + 1)

                }else{
                    val errorBody = response.errorBody()?.string()
                    Log.e("API Error", errorBody ?: "Unknown Error")
                }

            } catch (t: Throwable) {
                Log.e("Network Error", t.message ?: "Unknown Error")
            }
        }
    }


}