package com.example.havucwallpapernewversion.screens.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetImagesCategoryUseCase
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetFavoriteImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryVM  @Inject constructor(
    private val getCategoryUseCase: GetImagesCategoryUseCase,
) : BaseViewModel() {

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList

    init {
        getCategory()
    }

    private fun getCategory(){
        viewModelScope.launch {
            val categoryList = getCategoryUseCase.invoke()
            _categoryList.postValue(categoryList.getOrNull())
        }
    }
}