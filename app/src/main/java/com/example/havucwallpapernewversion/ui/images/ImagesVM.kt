package com.example.havucwallpapernewversion.ui.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetCategoryDetailUseCase
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetFavoriteImageUseCase
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetImagesUseCase
import com.example.havucwallpapernewversion.features.images.domain.usecases.LikeAndUnLikeImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesVM @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val imageLikeAndUnLikeImageUseCase: LikeAndUnLikeImageUseCase,
    private val getFavoriteImageUseCase: GetFavoriteImageUseCase,
    private val getCategoryUseCase: GetCategoryDetailUseCase,
) : BaseViewModel() {

    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var currentPage: Int = 0


    fun getImage() {
        viewModelScope.launch {
            showLoading()
            val response = getImagesUseCase(currentPage)
            if (response.isSuccess) {
                hideLoading()
                val currentList = imageList.value?.toMutableList() ?: mutableListOf()
                val imageList = response.getOrNull()?.toMutableList() ?: mutableListOf()
                currentList.addAll(imageList)
                _imageList.postValue(currentList)
                currentPage += 1
            } else {
                _errorMessage.postValue(response.exceptionOrNull()?.message)
            }
        }
    }

    fun getDetailCategory(title: String) {
        showLoading()
        viewModelScope.launch {
            val categoryList = getCategoryUseCase(currentPage, title)
            hideLoading()
            val currentList = imageList.value?.toMutableList() ?: mutableListOf()
            val imageList = categoryList.getOrNull()?.toMutableList() ?: mutableListOf()
            currentList.addAll(imageList)
            _imageList.postValue(currentList)
            currentPage += 1
        }
    }

    fun likeOrUnLike(image: Image) {
        viewModelScope.launch {
            imageLikeAndUnLikeImageUseCase(image)
        }
    }
    fun getFavoriteImage() {
        showLoading()
        viewModelScope.launch {
            val imageList = getFavoriteImageUseCase.invoke()
            hideLoading()
            _imageList.postValue(imageList)


        }
    }
}