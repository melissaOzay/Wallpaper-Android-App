package com.example.havucwallpapernewversion.screens.images.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.screens.categories.adapter.CategoryDiffCallBack
import com.squareup.picasso.Picasso

class ImagesAdapter(val listener: ImagesAdapterListener) :
    RecyclerView.Adapter<ImagesAdapter.CompanyViewHolder>() {

    private var images = arrayListOf<Image>()

    fun setListData(items: List<Image>) {
        val diffUtil = CategoryDiffCallBack(images, items)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        images = items as ArrayList<Image>
        diffResult.dispatchUpdatesTo(this)

    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photo: AppCompatImageView = view.findViewById(R.id.iv_photo)
        private val favoriteToggle: ToggleButton = view.findViewById(R.id.toggleButton)

        fun bindItems(item: Image, listener: ImagesAdapterListener) {
            Picasso.get().load(item.imagePullPath).into(photo)
            favoriteToggle.isChecked = item.isLiked
            favoriteToggle.setOnClickListener {
                    listener.addFavorite(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_image_screen, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(images[position], listener)

    }

    override fun getItemCount(): Int {
        return images.count()
    }

    interface ImagesAdapterListener {
        fun addFavorite(image: Image)
    }

}