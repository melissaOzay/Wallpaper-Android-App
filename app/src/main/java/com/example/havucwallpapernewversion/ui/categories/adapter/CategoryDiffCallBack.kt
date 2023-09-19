package com.example.havucwallpapernewversion.ui.categories.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.havucwallpapernewversion.features.categories.domain.model.Category

class CategoryDiffCallBack(
    private val oldList: List<Category>,
    private val newList: List<Category>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].categoryQuery == newList[newItemPosition].categoryQuery
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].categoryQuery != newList[newItemPosition].categoryQuery -> {
                false
            }

            oldList[oldItemPosition].categoryTitle != newList[newItemPosition].categoryTitle -> {
                false

            }

            else -> true
        }
    }
}
