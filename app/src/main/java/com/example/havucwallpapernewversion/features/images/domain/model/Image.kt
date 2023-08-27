package com.example.havucwallpapernewversion.features.images.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: String,
    val path: String,
    val imagePullPath:String
) : Parcelable