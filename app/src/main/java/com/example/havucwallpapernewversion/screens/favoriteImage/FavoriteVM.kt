package com.example.havucwallpapernewversion.screens.favoriteImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetImagesUseCase
import com.example.havucwallpapernewversion.features.images.domain.mapper.toImage
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
) : BaseViewModel() {


    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var currentPage: Int = 0


    fun getImage() {
        viewModelScope.launch {
            val response = getImagesUseCase(currentPage)
            if (response.isSuccess) {
                val images = response.getOrNull()
                val currentList = imageList.value?.toMutableList() ?: mutableListOf()
                val imageList = images?.toMutableList() ?: mutableListOf()
                currentList.addAll(imageList)
                _imageList.postValue(currentList)
                currentPage += 1
            } else {
               // _errorMessage.postValue(response.getOrNull()?.error)

            }


        }
    }


}