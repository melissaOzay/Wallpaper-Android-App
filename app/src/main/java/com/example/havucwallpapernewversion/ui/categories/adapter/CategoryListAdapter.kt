package com.example.havucwallpapernewversion.ui.categories.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.features.categories.domain.model.Category
import com.example.havucwallpapernewversion.ui.categories.adapter.`interface`.CategoryAdapterListener
import com.squareup.picasso.Picasso

class CategoryListAdapter(private val listener: CategoryAdapterListener) :
    RecyclerView.Adapter<CategoryListAdapter.CompanyViewHolder>() {

    private var category = arrayListOf<Category>()

    fun setListData(items: List<Category>) {
        val diffUtil = CategoryDiffCallBack(category, items)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        category = items as ArrayList<Category>
        diffResult.dispatchUpdatesTo(this)
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photo: AppCompatImageView = view.findViewById(R.id.iv_photo)
        private val title: TextView = view.findViewById(R.id.tv_title)
        private val count: TextView = view.findViewById(R.id.tv_count)

        fun bindItems(item: Category, listener: CategoryAdapterListener) {
            Picasso.get().load(item.image).into(photo)
            photo.setColorFilter(Color.argb(100, 255, 255, 255))
            title.text = item.categoryTitle
            count.text = "-${item.count} Wallpaper-"
            photo.setOnClickListener {
                listener.clickCategory(item.categoryTitle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_category, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(category[position], listener)

    }

    override fun getItemCount(): Int {
        return category.count()
    }

}