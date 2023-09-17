package com.example.havucwallpapernewversion.screens.categories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.havucwallpapernewversion.features.images.domain.model.Image

class CategoryDiffCallBack(
    private val oldList: List<Image>,
    private val newList: List<Image>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].isLiked != newList[newItemPosition].isLiked -> {
                false

            }

            else -> true
        }
    }
}
