package com.example.havucwallpapernewversion.screens.categoryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetCategoryDetailUseCase
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetImagesCategoryUseCase
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetImagesUseCase
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.features.images.domain.usecases.LikeAndUnLikeImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailVM @Inject constructor(
    private val imageLikeAndUnLikeImageUseCase: LikeAndUnLikeImageUseCase,
    private val getCategoryUseCase: GetCategoryDetailUseCase,

    ) : BaseViewModel() {

    private val _categoryDetailList = MutableLiveData<List<Image>>()
    val categoryDetailList: LiveData<List<Image>> get() = _categoryDetailList


    private var currentPage: Int = 0

     fun getDetailCategory(title:String){
        viewModelScope.launch {
            val categoryList = getCategoryUseCase(currentPage,title)
            val currentList = categoryDetailList.value?.toMutableList() ?: mutableListOf()
            val imageList = categoryList.getOrNull()?.toMutableList() ?: mutableListOf()
            currentList.addAll(imageList)
            _categoryDetailList.postValue(currentList)
            currentPage += 1
        }
    }

    fun addFavorite(image: Image) {
        imageLikeAndUnLikeImageUseCase(image)
    }


}