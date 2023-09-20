package com.example.havucwallpapernewversion.ui.images.adapter.`interface`

import com.example.havucwallpapernewversion.features.images.domain.model.Image


interface ImagesAdapterListener {
    fun likeOrUnLike(image: Image)
}