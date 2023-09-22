package com.example.havucwallpapernewversion.ui.imageDetail

import androidx.lifecycle.viewModelScope
import com.example.havucwallpapernewversion.base.BaseViewModel
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.features.images.domain.usecases.LikeAndUnLikeImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailVM @Inject constructor(
    private val imageLikeAndUnLikeImageUseCase: LikeAndUnLikeImageUseCase,
) : BaseViewModel(){

fun likeOrUnLike(image: Image) {
    viewModelScope.launch {
        imageLikeAndUnLikeImageUseCase(image)
    }
}
}