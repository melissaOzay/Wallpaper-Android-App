package com.example.havucwallpapernewversion.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import com.example.havucwallpapernewversion.features.categories.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryVM @Inject constructor(
    private val getCategoryUseCase: GetCategoriesUseCase,
) : BaseViewModel() {

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList


    init {
        getCategory()
    }

    private fun getCategory() {
        showLoading()
        viewModelScope.launch {
            getCategoryUseCase.invoke().collect{
                hideLoading()
                _categoryList.postValue(it.getOrElse {
                    emptyList()
                })
            }

        }
    }

}