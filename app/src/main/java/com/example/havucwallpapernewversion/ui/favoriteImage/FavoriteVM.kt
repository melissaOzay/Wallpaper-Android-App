package com.example.havucwallpapernewversion.ui.favoriteImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.features.images.domain.usecases.GetFavoriteImageUseCase
import com.example.havucwallpapernewversion.features.images.domain.usecases.LikeAndUnLikeImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    private val getFavoriteImageUseCase: GetFavoriteImageUseCase,
    private val imageLikeAndUnLikeImageUseCase: LikeAndUnLikeImageUseCase,

    ) : BaseViewModel() {


    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    fun getFavoriteImage() {
        showLoading()
        viewModelScope.launch {
            val imageList = getFavoriteImageUseCase.invoke()
            hideLoading()
            _imageList.postValue(imageList)


        }
    }

    fun likeOrUnLike(image: Image) {
        viewModelScope.launch {
            imageLikeAndUnLikeImageUseCase(image)
        }
    }

}