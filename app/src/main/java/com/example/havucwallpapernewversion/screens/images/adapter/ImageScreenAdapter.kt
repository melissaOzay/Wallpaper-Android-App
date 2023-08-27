package com.example.havucwallpapernewversion.screens.images.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.features.images.data.model.ImageResponse
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.squareup.picasso.Picasso

class ImageScreenAdapter :
    RecyclerView.Adapter<ImageScreenAdapter.CompanyViewHolder>() {

    private var items = ArrayList<Image>()

    fun setListData(items: List<Image>) {
        this.items = ArrayList(items)
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo = view.findViewById<AppCompatImageView>(R.id.iv_photo)

        fun bindItems(item: Image) {
            Picasso.get().load(item.imagePullPath).into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_image_screen, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(items.get(position))


    }

    override fun getItemCount(): Int {
        return items.count()
    }

 /*   interface HomeAdapterListener {
        fun addFavorite(entity: WordyEntity)
        fun deleteFavorite(entity: WordyEntity)
        fun shareButton(text: String)
        fun copyToClipboard(text: CharSequence)
    }*/

}