package com.example.havucwallpapernewversion.screens.favoriteImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetFavoriteImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    private val getFavoriteImageUseCase: GetFavoriteImageUseCase,
) : BaseViewModel() {


    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    fun getFavoriteImage() {
        viewModelScope.launch {
            val imageList = getFavoriteImageUseCase.invoke()
            _imageList.postValue(imageList)
        }

    }

}